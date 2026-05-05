import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MetricsSummary } from './metrics-summary';

describe('MetricsSummary', () => {
  let component: MetricsSummary;
  let fixture: ComponentFixture<MetricsSummary>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MetricsSummary]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MetricsSummary);
    fixture.componentRef.setInput('kpis', [
      {
        id: 'kpi-1',
        label: 'Solicitudes',
        value: '42',
        change: '+12%',
        changeType: 'positive',
        icon: 'requests',
        description: 'Resumen rápido'
      }
    ]);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
