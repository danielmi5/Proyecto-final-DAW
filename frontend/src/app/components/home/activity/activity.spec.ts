import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { Activity } from './activity';

describe('Activity', () => {
  let component: Activity;
  let fixture: ComponentFixture<Activity>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Activity],
      providers: [provideRouter([])]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Activity);
    fixture.componentRef.setInput('activities', [
      {
        id: 'act-1',
        type: 'analysis',
        title: 'Análisis inicial',
        description: 'Validación de impacto',
        user: 'Copilot',
        time: 'hace 1 min'
      }
    ]);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});