import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

const EMAIL_TLD_PATTERN = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;

export function emailTldValidator(): ValidatorFn {
	return (control: AbstractControl): ValidationErrors | null => {
		const value = control.value;

		if (value == null || value === '') {
			return null;
		}

		return EMAIL_TLD_PATTERN.test(value) ? null : { email: true };
	};
}
