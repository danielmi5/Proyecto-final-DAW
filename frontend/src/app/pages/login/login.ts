import { ChangeDetectionStrategy, Component } from '@angular/core';
import { FormAside } from '../../components/form/form-aside/form-aside';
import { LoginForm } from '../../components/form/login-form/login-form';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormAside, LoginForm],
  templateUrl: './login.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginPage {}
