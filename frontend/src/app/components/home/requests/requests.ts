import { Component, ChangeDetectionStrategy, input } from '@angular/core';
import { TitleCasePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Button } from '../../shared/button/button';
import { Badge } from '../../shared/badge/badge';
import { FeatherIconDirective } from '../../../directives/feather-icon.directive';
import { PendingRequest } from '../../../pages/home/home.models';

@Component({
  selector: 'app-requests',
  imports: [TitleCasePipe, RouterLink, Button, Badge, FeatherIconDirective],
  templateUrl: './requests.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Requests {
  readonly requests = input.required<PendingRequest[]>();
}
