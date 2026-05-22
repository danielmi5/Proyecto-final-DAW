import { Component, Input, Output, EventEmitter, ViewChild, ElementRef, AfterContentInit, ContentChildren, QueryList, forwardRef, inject, Renderer2 } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-form-checkbox',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './form-checkbox.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FormCheckbox),
      multi: true
    }
  ]
})
export class FormCheckbox implements ControlValueAccessor, AfterContentInit {
  @Input() id: string = '';
  @Input() name: string = '';
  @Input() label: string = '';
  @Input() required: boolean = false;
  @Input() disabled: boolean = false;
  @Input() checked: boolean = false;

  @Output() checkedChange = new EventEmitter<boolean>();

  @ViewChild('checkboxInput', { static: false }) checkboxInput?: ElementRef<HTMLInputElement>;

  private renderer = inject(Renderer2);

  @ContentChildren('*', { read: ElementRef }) projectedElements?: QueryList<ElementRef>;

  hasProjected: boolean = false;

  onChange: (value: boolean) => void = () => {};
  onTouched: () => void = () => {};

  ngAfterContentInit() {
    this.hasProjected = !!(this.projectedElements && this.projectedElements.length > 0);
  }

  writeValue(value: boolean): void {
    this.checked = !!value;
    if (this.checkboxInput?.nativeElement) {
      try {
        this.renderer.setProperty(this.checkboxInput.nativeElement, 'checked', this.checked);
      } catch {
        this.checkboxInput.nativeElement.checked = this.checked;
      }
    }
  }

  registerOnChange(fn: (value: boolean) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  onNativeChange(event: Event) {
    const checkbox = event.target as HTMLInputElement;
    this.checked = !!checkbox.checked;
    this.onChange(this.checked);
    this.onTouched();
    this.checkedChange.emit(this.checked);
  }

  onVisualClick(event: MouseEvent) {
    if (this.disabled) {
      event.preventDefault();
      event.stopPropagation();
      return;
    }

    event.preventDefault();
    event.stopPropagation();

    this.checked = !this.checked;

    if (this.checkboxInput && this.checkboxInput.nativeElement) {
      try {
        this.renderer.setProperty(this.checkboxInput.nativeElement, 'checked', this.checked);
      } catch {
        this.checkboxInput.nativeElement.checked = this.checked;
      }
    }

    this.onChange(this.checked);
    this.onTouched();
    this.checkedChange.emit(this.checked);
  }
}
