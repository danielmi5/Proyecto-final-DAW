import { Component, Input, forwardRef, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, ReactiveFormsModule } from '@angular/forms';
import { FeatherIconDirective } from '../../../directives/feather-icon.directive';

export type ValidationState = 'initial' | 'warning' | 'error' | 'success';

@Component({
  selector: 'app-form-input',
  standalone: true,
  imports: [ReactiveFormsModule, FeatherIconDirective],
  templateUrl: './form-input.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FormInput),
      multi: true
    }
  ]
})
export class FormInput implements ControlValueAccessor {
[x: string]: any;
  @Input() type: string = 'text';
  @Input() name: string = '';
  @Input() id: string = '';
  @Input() label: string = '';
  @Input() placeholder: string = '';
  @Input() required: boolean = false;
  @Input() helpText?: string;
  @Input() errorMessage?: string;
  @Input() successMessage?: string;
  @Input() showSuccessMessage: boolean = false;
  @Input() warningMessage?: string;
  @Input() validationState: ValidationState = 'initial';
  @Input() hasError: boolean = false;
  @Input() success: boolean = false;
  @Input() disabled: boolean = false;
  @Input() leftIcon?: string;
  @Input() rightIcon?: string;
  @Input() min?: string;
  @Input() max?: string;

  displayType: string = 'text';

  ngOnInit(): void {
    this.displayType = this.type || 'text';
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['type'] && !changes['type'].firstChange) {
      this.displayType = changes['type'].currentValue || 'text';
    }
  }

  togglePasswordVisibility(): void {
    if (!this.displayType) this.displayType = this.type || 'text';
    this.displayType = this.displayType === 'password' ? 'text' : 'password';
  }

  getRightIconEffective(): string | null {
    if (this.rightIcon) return this.rightIcon;
    const state = this.getEffectiveState();
    if (state === 'success') return 'check';
    if (state === 'error') return 'alert-circle';
    if (state === 'warning') return 'alert-triangle';
    return null;
  }

  getPasswordToggleIcon(): string {
    return this.displayType === 'password' ? 'eye' : 'eye-off';
  }

  getEffectiveState(): ValidationState {
    if (this.validationState !== 'initial') {
      return this.validationState;
    }
    if (this.hasError) return 'error';
    if (this.success) return 'success';
    return 'initial';
  }

  value: string = '';
  onChange: any = () => {};
  onTouched: any = () => {};

  writeValue(value: any): void {
    this.value = value || '';
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  onInput(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    this.value = inputElement.value;
    this.onChange(this.value);
  }

  onBlur(): void {
    this.onTouched();
  }
}
