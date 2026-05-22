import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { AppStateService, AppUser } from './app-state.service';
import {
  AuthResponse,
  DecodedToken,
  LoginRequest,
  RegisterRequest,
  TokenResponse
} from '../models/auth.models';

const TOKEN_KEY = 'auth_token';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly appState = inject(AppStateService);
  private readonly router = inject(Router);
  private readonly apiUrl = environment.apiUrl;

  constructor() {
    this.restoreSession();
  }

  login(credentials: LoginRequest): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.apiUrl}/auth/token`, credentials).pipe(
      tap((response) => this.handleAuthSuccess(response.accessToken))
    );
  }

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/users`, {
      name: data.name,
      email: data.email,
      password: data.password,
      role: 'ANALYST'
    });
  }

  logout(): void {
    const token = this.getToken();
    if (token) {
      this.http.post(`${this.apiUrl}/auth/logout`, {}).subscribe({ error: () => undefined });
    }
    localStorage.removeItem(TOKEN_KEY);
    this.appState.setUser(null);
    void this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return !!this.getToken() && !this.isTokenExpired(this.getToken()!);
  }

  getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  private handleAuthSuccess(token: string): void {
    this.setToken(token);
    this.appState.setUser(this.decodeUser(token));
  }

  private setToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);
  }

  private restoreSession(): void {
    const token = this.getToken();
    if (!token || this.isTokenExpired(token)) {
      localStorage.removeItem(TOKEN_KEY);
      return;
    }
    this.appState.setUser(this.decodeUser(token));
  }

  private decodeUser(token: string): AppUser {
    const payload = this.decodeToken(token);
    return {
      email: payload.sub,
      name: payload.name,
      role: payload.roles?.[0]?.authority
    };
  }

  private decodeToken(token: string): DecodedToken {
    const payload = token.split('.')[1];
    if (!payload) {
      throw new Error('Token JWT inválido');
    }
    return JSON.parse(atob(payload)) as DecodedToken;
  }

  private isTokenExpired(token: string): boolean {
    try {
      const { exp } = this.decodeToken(token);
      return Date.now() >= exp * 1000;
    } catch {
      return true;
    }
  }
}
