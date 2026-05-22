import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function complexityValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (value !== null && (isNaN(value) || value < 1 || value > 10)) {
      return { invalidComplexity: true };
    }
    return null;
  };
}
