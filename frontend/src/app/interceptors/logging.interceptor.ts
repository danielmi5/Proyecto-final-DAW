import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { tap, catchError, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';

export const loggingInterceptor: HttpInterceptorFn = (req, next) => {
  const started = Date.now();

  return next(req).pipe(
    tap((event) => {
      if (event.type === 4) {
        const elapsed = Date.now() - started;
        console.info(`[HTTP] ${req.method} ${req.url} - ${elapsed}ms`);
      }
    }),
    catchError((error) => {
      const elapsed = Date.now() - started;
      const status = error.status ?? 'ERR';
      console.error(`[HTTP] ${req.method} ${req.url} - ${status} (${elapsed}ms)`, error);
      return throwError(() => error);
    })
  );
};

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = inject(AuthService).getToken();

  if (token && !req.url.includes('/api/auth/token')) {
    req = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }

  return next(req);
};
