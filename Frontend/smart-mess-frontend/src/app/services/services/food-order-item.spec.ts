import { TestBed } from '@angular/core/testing';

import { FoodOrderItem } from './food-order-item';

describe('FoodOrderItem', () => {
  let service: FoodOrderItem;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodOrderItem);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
