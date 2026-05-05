import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SummaryCard } from './summary-card';

describe('SummaryCard', () => {
  let component: SummaryCard;
  let fixture: ComponentFixture<SummaryCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SummaryCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SummaryCard);
    fixture.componentRef.setInput('summary', {
      id: 'summary-1',
      value: '24h',
      label: 'Ventana',
      tone: 'info',
      icon: 'clock'
    });
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});