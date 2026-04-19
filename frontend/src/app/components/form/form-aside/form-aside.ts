import { ChangeDetectionStrategy, Component, input } from '@angular/core';

@Component({
	selector: 'app-form-aside',
	standalone: true,
	templateUrl: './form-aside.html',
	changeDetection: ChangeDetectionStrategy.OnPush
})
export class FormAside {
	readonly eyebrow = input<string>('');
	readonly title = input<string>('');
	readonly subtitle = input<string>('');
}
