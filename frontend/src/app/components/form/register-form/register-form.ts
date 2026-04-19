import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Button } from '../../shared/button/button';
import { FormCheckbox } from '../../shared/form-checkbox/form-checkbox';
import { FormInput } from '../../shared/form-input/form-input';
import { RegisterEmailAvailabilityService } from '../../../form/services/validadores-asincronos.service';
import { emailTldValidator, getRegisterFieldMessage, getRegisterFieldState, getRegisterSubmitLabel, passwordMatchValidator, passwordStrengthValidator, type RegisterFieldName, type RegisterFieldState } from '../../../form/validators';

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
			console.log('Formulario incorrecto');
			return;
		}

		console.log('Formulario enviado');
	}
}
