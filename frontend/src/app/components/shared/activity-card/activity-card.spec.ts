import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityCard } from './activity-card';

describe('ActivityCard', () => {
  let component: ActivityCard;
  let fixture: ComponentFixture<ActivityCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActivityCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivityCard);
    fixture.componentRef.setInput('activity', {
      id: 'act-1',
      type: 'analysis',
      title: 'Análisis inicial',
      description: 'Validación de impacto',
      user: 'Copilot',
      time: 'hace 1 min'
    });
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});