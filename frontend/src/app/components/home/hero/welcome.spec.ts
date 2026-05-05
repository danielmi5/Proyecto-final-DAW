import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { DashboardHero } from './welcome';

describe('DashboardHero', () => {
  let component: DashboardHero;
  let fixture: ComponentFixture<DashboardHero>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardHero],
      providers: [provideRouter([])]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardHero);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
