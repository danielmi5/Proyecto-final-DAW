import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export interface PasswordStrengthError {
  hasMinimumLength: boolean;
  hasLetter: boolean;
  hasNumber: boolean;
  hasSymbol: boolean;
}

export function passwordStrengthValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;

    if (!value) {
      return null;
    }

    const hasMinimumLength = value.length >= 8;
    const hasLetter = /[a-zA-Z]/.test(value);
    const hasNumber = /\d/.test(value);
    const hasSymbol = /[!@#$%^&*(),.?":{}|<>_\-+=\[\]\\\/`~;']/.test(value);

    if (hasMinimumLength && hasLetter && hasNumber && hasSymbol) {
      return null;
    }

    return {
      passwordStrength: {
        hasMinimumLength,
        hasLetter,
        hasNumber,
        hasSymbol
      } satisfies PasswordStrengthError
    };
  };
}
