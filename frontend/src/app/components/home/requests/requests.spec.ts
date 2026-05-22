import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { Requests } from './requests';

describe('Requests', () => {
  let component: Requests;
  let fixture: ComponentFixture<Requests>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Requests],
      providers: [provideRouter([])]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Requests);
    fixture.componentRef.setInput('requests', [
      {
        id: 'req-1',
        title: 'Solicitud demo',
        source: 'ERP',
        date: '2026-05-22',
        priority: 'medium'
      }
    ]);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});