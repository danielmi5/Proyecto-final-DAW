import { Component, ChangeDetectionStrategy, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ActivityCard } from '../../shared/activity-card/activity-card';
import { FeatherIconDirective } from '../../../directives/feather-icon.directive';
import { ActivityItem } from '../../../pages/home/home.models';

@Component({
  selector: 'app-activity',
  imports: [RouterLink, ActivityCard, FeatherIconDirective],
  templateUrl: './activity.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Activity {
  readonly activities = input.required<ActivityItem[]>();
}
