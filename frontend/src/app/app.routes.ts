import { Routes } from '@angular/router';
import { authGuard, publicGuard } from './guards';

export const routes: Routes = [
	{
		path: '',
		loadComponent: () => import('./pages/home/home').then((m) => m.HomePage),
		title: 'Inicio'
	},
	{
		path: 'register',
		loadComponent: () => import('./pages/register/register').then((m) => m.RegisterPage),
		canActivate: [publicGuard],
		title: 'Registro'
	},
	{
		path: 'login',
		loadComponent: () => import('./pages/login/login').then((m) => m.LoginPage),
		canActivate: [publicGuard],
		title: 'Login'
	},
	{
		path: 'home',
		redirectTo: '',
		pathMatch: 'full'
	},
	{
		path: '**',
		redirectTo: ''
	}
];
