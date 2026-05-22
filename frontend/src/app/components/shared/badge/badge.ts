import { Component, ChangeDetectionStrategy, computed, input } from '@angular/core';

@Component({
  selector: 'app-badge',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './badge.html'
})
export class Badge {
  readonly baseClass = input.required<string>();
  readonly tone = input<string | null>(null);

  readonly badgeClasses = computed(() => {
    const tone = this.tone();
    return tone ? `${this.baseClass()} ${this.baseClass()}--${tone}` : this.baseClass();
  });
}
