import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodItemAdd } from './food-item-add';

describe('FoodItemAdd', () => {
  let component: FoodItemAdd;
  let fixture: ComponentFixture<FoodItemAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodItemAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(FoodItemAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
