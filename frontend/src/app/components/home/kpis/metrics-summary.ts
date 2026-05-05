import { Component, ChangeDetectionStrategy, input } from '@angular/core';
import { KpiCardCmp } from '../../shared/kpi-card/kpi-card';
import { KpiCard } from '../../../pages/home/home.models';

@Component({
  selector: 'app-metrics-summary',
  imports: [KpiCardCmp],
  templateUrl: './metrics-summary.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class MetricsSummary {
  readonly kpis = input.required<KpiCard[]>();
}
