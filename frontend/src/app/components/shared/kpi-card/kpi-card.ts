import { Component, ChangeDetectionStrategy, computed, input } from '@angular/core';
import { FeatherIconDirective } from '../../../directives/feather-icon.directive';
import { KpiCard } from '../../../pages/home/home.models';

@Component({
  selector: 'app-kpi-card',
  imports: [FeatherIconDirective],
  templateUrl: './kpi-card.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class KpiCardCmp {
  readonly kpi = input.required<KpiCard>();

  readonly kpiIcon = computed(() => {
    switch (this.kpi().icon) {
      case 'requests':
        return 'layers';
      case 'analysis':
        return 'slack';
      case 'documents':
        return 'file-text';
    }
  });
}
