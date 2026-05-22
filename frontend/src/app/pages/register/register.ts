import { ChangeDetectionStrategy, Component } from '@angular/core';
import { FormAside } from '../../components/form/form-aside/form-aside';
import { RegisterForm } from '../../components/form/register-form/register-form';

@Component({
	selector: 'app-register',
	standalone: true,
	imports: [FormAside, RegisterForm],
	templateUrl: './register.html',
	changeDetection: ChangeDetectionStrategy.OnPush
})
export class RegisterPage {}
