import { Component, ChangeDetectionStrategy, input } from '@angular/core';
import { FeatherIconDirective } from '../../../directives/feather-icon.directive';
import { SummaryMetric } from '../../../pages/home/home.models';

@Component({
  selector: 'app-summary-card',
  imports: [FeatherIconDirective],
  templateUrl: './summary-card.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SummaryCard {
  readonly summary = input.required<SummaryMetric>();
}
