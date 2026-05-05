import { Component, ChangeDetectionStrategy, computed, input } from '@angular/core';
import { FeatherIconDirective } from '../../../directives/feather-icon.directive';
import { ActivityItem } from '../../../pages/home/home.models';

@Component({
  selector: 'app-activity-card',
  imports: [FeatherIconDirective],
  templateUrl: './activity-card.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ActivityCard {
  readonly activity = input.required<ActivityItem>();

  readonly activityIcon = computed(() => {
    switch (this.activity().type) {
      case 'document':
        return 'file-text';
      case 'estimation':
        return 'clock';
      case 'analysis':
        return 'layers';
    }
  });
}
