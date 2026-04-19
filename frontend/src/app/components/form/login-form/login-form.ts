import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Button } from '../../shared/button/button';
import { FormInput } from '../../shared/form-input/form-input';
import { emailTldValidator, getRegisterFieldMessage, getRegisterFieldState, type RegisterFieldName, type RegisterFieldState } from '../../../form/validators';

@Component({
	selector: 'app-login-form',
	standalone: true,
	imports: [ReactiveFormsModule, RouterLink, Button, FormInput],
	templateUrl: './login-form.html',
	changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginForm {
	private readonly fb = inject(FormBuilder);

	readonly form = this.fb.nonNullable.group({
		email: ['', [Validators.required, emailTldValidator()]],
		password: ['', [Validators.required]]
	});

	fieldState(fieldName: RegisterFieldName): RegisterFieldState {
		return getRegisterFieldState(this.form.get(fieldName));
	}

	fieldMessage(fieldName: RegisterFieldName, state: RegisterFieldState): string {
		return getRegisterFieldMessage(fieldName, this.form.get(fieldName), state);
	}

	get submitLabel(): string {
		return this.form.pending ? 'Validando...' : 'Iniciar sesión';
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
