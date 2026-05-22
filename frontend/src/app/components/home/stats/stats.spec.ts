import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Stats } from './stats';

describe('Stats', () => {
  let component: Stats;
  let fixture: ComponentFixture<Stats>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Stats]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Stats);
    fixture.componentRef.setInput('statMetrics', [
      {
        id: 'stat-1',
        label: 'Procesados',
        value: 10,
        maxValue: 20,
        color: 'var(--color-primary-base)'
      }
    ]);
    fixture.componentRef.setInput('summaryMetrics', [
      {
        id: 'summary-1',
        value: '24h',
        label: 'Ventana',
        tone: 'info',
        icon: 'clock'
      }
    ]);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});