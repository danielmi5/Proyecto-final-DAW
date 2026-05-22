import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAside } from './form-aside';

describe('FormAside', () => {
  let component: FormAside;
  let fixture: ComponentFixture<FormAside>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormAside]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAside);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});