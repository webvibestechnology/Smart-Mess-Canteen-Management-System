import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealEdit } from './meal-edit';

describe('MealEdit', () => {
  let component: MealEdit;
  let fixture: ComponentFixture<MealEdit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MealEdit],
    }).compileComponents();

    fixture = TestBed.createComponent(MealEdit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
