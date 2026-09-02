import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CanteenAdd } from './canteen-add';

describe('CanteenAdd', () => {
  let component: CanteenAdd;
  let fixture: ComponentFixture<CanteenAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CanteenAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(CanteenAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
