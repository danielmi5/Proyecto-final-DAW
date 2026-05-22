import { Component, ChangeDetectionStrategy, computed, input } from '@angular/core';
import { StatMetric } from '../../../pages/home/home.models';

@Component({
  selector: 'app-stat-card',
  templateUrl: './stat-card.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class StatCard {
  readonly stat = input.required<StatMetric>();

  readonly progress = computed(() => (this.stat().value / this.stat().maxValue) * 100);
}
