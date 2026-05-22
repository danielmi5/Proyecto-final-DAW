import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

function setPasswordMismatchError(control: AbstractControl, hasError: boolean): void {
  const errors = { ...(control.errors ?? {}) };

  if (hasError) {
	  errors['passwordMismatch'] = true;
    control.setErrors(errors);
    return;
  }

	if (!errors['passwordMismatch']) {
    return;
  }

	delete errors['passwordMismatch'];
  control.setErrors(Object.keys(errors).length ? errors : null);
}

export function passwordMatchValidator(passwordControlName = 'password', confirmPasswordControlName = 'confirmPassword'): ValidatorFn {
  return (group: AbstractControl): ValidationErrors | null => {
    const passwordControl = group.get(passwordControlName);
    const confirmPasswordControl = group.get(confirmPasswordControlName);

    if (!passwordControl || !confirmPasswordControl) {
      return null;
    }

    if (!passwordControl.value || !confirmPasswordControl.value) {
      setPasswordMismatchError(confirmPasswordControl, false);
      return null;
    }

    const mismatch = passwordControl.value !== confirmPasswordControl.value;
    setPasswordMismatchError(confirmPasswordControl, mismatch);

    return mismatch ? { passwordMismatch: true } : null;
  };
}
