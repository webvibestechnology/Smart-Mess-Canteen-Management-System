import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealAdd } from './meal-add';

describe('MealAdd', () => {
  let component: MealAdd;
  let fixture: ComponentFixture<MealAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MealAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(MealAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
