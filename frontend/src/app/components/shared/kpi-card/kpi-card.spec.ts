import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KpiCardCmp } from './kpi-card';

describe('KpiCardCmp', () => {
  let component: KpiCardCmp;
  let fixture: ComponentFixture<KpiCardCmp>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KpiCardCmp]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KpiCardCmp);
    fixture.componentRef.setInput('kpi', {
      id: 'kpi-1',
      label: 'Solicitudes',
      value: '42',
      change: '+12%',
      changeType: 'positive',
      icon: 'requests',
      description: 'Resumen rápido'
    });
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});