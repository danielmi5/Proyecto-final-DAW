import { Injectable, computed, signal } from '@angular/core';
import { getUserInitials } from '../utils/user-initials';

export interface AppUser {
  email: string;
  name?: string;
  role?: string;
}

@Injectable({ providedIn: 'root' })
export class AppStateService {
  private readonly _isLoading = signal(false);
  private readonly _isOnline = signal(typeof navigator !== 'undefined' ? navigator.onLine : true);
  private readonly _user = signal<AppUser | null>(null);
  private readonly _error = signal<string | null>(null);

  readonly isLoading = this._isLoading.asReadonly();
  readonly isOnline = this._isOnline.asReadonly();
  readonly user = this._user.asReadonly();
  readonly error = this._error.asReadonly();
  readonly isAuthenticated = computed(() => this._user() !== null);
  readonly userInitials = computed(() => getUserInitials(this._user()?.name));

  constructor() {
    if (typeof window !== 'undefined') {
      window.addEventListener('online', () => this._isOnline.set(true));
      window.addEventListener('offline', () => this._isOnline.set(false));
    }
  }

  setLoading(value: boolean): void {
    this._isLoading.set(value);
  }

  setUser(user: AppUser | null): void {
    this._user.set(user);
  }

  getUser(): AppUser | null {
    return this._user();
  }

  setError(message: string | null): void {
    this._error.set(message);
  }
}
