import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Button } from '../../shared/button/button';
import { FormCheckbox } from '../../shared/form-checkbox/form-checkbox';
import { FormInput } from '../../shared/form-input/form-input';
import { RegisterEmailAvailabilityService } from '../../../form/services/validadores-asincronos.service';
import { emailTldValidator, getRegisterFieldMessage, getRegisterFieldState, getRegisterSubmitLabel, passwordMatchValidator, passwordStrengthValidator, type RegisterFieldName, type RegisterFieldState } from '../../../form/validators';
import { AppStateService, AuthService } from '../../../services';

@Component({
	selector: 'app-register-form',
	standalone: true,
	imports: [ReactiveFormsModule, RouterLink, Button, FormInput, FormCheckbox],
	templateUrl: './register-form.html',
	changeDetection: ChangeDetectionStrategy.OnPush
})
export class RegisterForm {
	private readonly fb = inject(FormBuilder);
	private readonly emailAvailability = inject(RegisterEmailAvailabilityService);
	private readonly auth = inject(AuthService);
	private readonly appState = inject(AppStateService);
	private readonly router = inject(Router);

	readonly form = this.fb.nonNullable.group(
		{
			name: ['', [Validators.required, Validators.minLength(2)]],
			email: ['', {
				validators: [Validators.required, emailTldValidator()],
				asyncValidators: [this.emailAvailability.emailAvailabilityValidator()]
			}],
			password: ['', [Validators.required, Validators.minLength(8), passwordStrengthValidator()]],
			confirmPassword: ['', [Validators.required]],
			acceptTerms: [false, [Validators.requiredTrue]]
		},
		{ validators: passwordMatchValidator('password', 'confirmPassword') }
	);

	fieldState(fieldName: RegisterFieldName): RegisterFieldState {
		return getRegisterFieldState(this.form.get(fieldName));
	}

	fieldMessage(fieldName: RegisterFieldName, state: RegisterFieldState): string {
		return getRegisterFieldMessage(fieldName, this.form.get(fieldName), state);
	}

	get submitLabel(): string {
		return getRegisterSubmitLabel(this.form.pending);
	}

	onSubmit(): void {
		this.form.markAllAsTouched();

		if (this.form.invalid) {
			return;
		}

		const { name, email, password } = this.form.getRawValue();
		this.appState.setLoading(true);
		this.appState.setError(null);

		this.auth.register({ name, email, password }).subscribe({
			next: () => {
				this.appState.setLoading(false);
				void this.router.navigate(['/login'], {
					queryParams: { email, registered: 'true' }
				});
			},
			error: () => {
				this.appState.setLoading(false);
				this.appState.setError('No se pudo completar el registro');
			}
		});
	}
}
