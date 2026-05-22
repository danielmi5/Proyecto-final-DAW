import { Component, ChangeDetectionStrategy, input } from '@angular/core';
import { StatCard } from '../../shared/stat-card/stat-card';
import { SummaryCard } from '../../shared/summary-card/summary-card';
import { StatMetric, SummaryMetric } from '../../../pages/home/home.models';

@Component({
  selector: 'app-stats',
  imports: [StatCard, SummaryCard],
  templateUrl: './stats.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Stats {
  readonly statMetrics = input.required<StatMetric[]>();
  readonly summaryMetrics = input.required<SummaryMetric[]>();
}
