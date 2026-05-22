import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestStateCard } from './request-state-card';

describe('RequestStateCard', () => {
  let component: RequestStateCard;
  let fixture: ComponentFixture<RequestStateCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequestStateCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestStateCard);
    fixture.componentRef.setInput('badge', 'Pendiente');
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
