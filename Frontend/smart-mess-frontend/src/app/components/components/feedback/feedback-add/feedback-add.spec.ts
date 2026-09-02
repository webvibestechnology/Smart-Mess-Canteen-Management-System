import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackAdd } from './feedback-add';

describe('FeedbackAdd', () => {
  let component: FeedbackAdd;
  let fixture: ComponentFixture<FeedbackAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeedbackAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(FeedbackAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
