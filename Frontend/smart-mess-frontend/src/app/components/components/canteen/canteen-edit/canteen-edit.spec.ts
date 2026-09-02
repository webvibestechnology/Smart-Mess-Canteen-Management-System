import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CanteenEdit } from './canteen-edit';

describe('CanteenEdit', () => {
  let component: CanteenEdit;
  let fixture: ComponentFixture<CanteenEdit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CanteenEdit],
    }).compileComponents();

    fixture = TestBed.createComponent(CanteenEdit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
