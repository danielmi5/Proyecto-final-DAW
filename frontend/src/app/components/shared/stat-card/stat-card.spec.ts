import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatCard } from './stat-card';

describe('StatCard', () => {
  let component: StatCard;
  let fixture: ComponentFixture<StatCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StatCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatCard);
    fixture.componentRef.setInput('stat', {
      id: 'stat-1',
      label: 'Procesados',
      value: 10,
      maxValue: 20,
      color: 'var(--color-primary-base)'
    });
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});