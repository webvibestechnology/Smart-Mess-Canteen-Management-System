import { TestBed } from '@angular/core/testing';

import { Canteen } from './canteen';

describe('Canteen', () => {
  let service: Canteen;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Canteen);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
