import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodOrderAdd } from './food-order-add';

describe('FoodOrderAdd', () => {
  let component: FoodOrderAdd;
  let fixture: ComponentFixture<FoodOrderAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodOrderAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(FoodOrderAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
