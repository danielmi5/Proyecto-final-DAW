import { Component, ChangeDetectionStrategy, computed, input } from '@angular/core';
import { Badge } from '../badge/badge';

@Component({
  selector: 'app-request-state-card',
  imports: [Badge],
  templateUrl: './request-state-card.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RequestStateCard {
  readonly badge = input.required<string>();
  readonly status = input<string | null>(null);
  readonly scoreLabel = input<string | null>('Impacto estimado');
  readonly scoreValue = input<string | null>('24h');
  readonly timelineCount = input<number | null>(4);
  readonly activeIndex = input<number | null>(2);
  readonly insight = input<string | null>(null);

  readonly timelineStates = computed(() => {
    const count = this.timelineCount() ?? 0;
    const active = this.activeIndex() ?? -1;
    return Array.from({ length: count }).map((_, i) => (i < active ? 'is-complete' : i === active ? 'is-active' : ''));
  });
}
