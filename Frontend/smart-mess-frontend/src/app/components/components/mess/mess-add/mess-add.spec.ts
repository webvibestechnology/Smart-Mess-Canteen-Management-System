import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessAdd } from './mess-add';

describe('MessAdd', () => {
  let component: MessAdd;
  let fixture: ComponentFixture<MessAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MessAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(MessAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
