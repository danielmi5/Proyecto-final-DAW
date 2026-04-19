import { Injectable } from '@angular/core';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { Observable, of, map } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class RegisterEmailAvailabilityService {
  checkEmailAvailability(email: string): Observable<boolean> {
    return of(true);
  }

  emailAvailabilityValidator(): AsyncValidatorFn {
    return (control: AbstractControl) => {
      const email = String(control.value ?? '').trim();

      if (!email) {
        return of(null);
      }

      return this.checkEmailAvailability(email).pipe(map((available: boolean) => (available ? null : { emailTaken: true })));
    };
  }
}
