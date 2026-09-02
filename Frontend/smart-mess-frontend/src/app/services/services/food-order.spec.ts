import { TestBed } from '@angular/core/testing';

import { FoodOrder } from './food-order';

describe('FoodOrder', () => {
  let service: FoodOrder;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodOrder);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
