import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceAdd } from './attendance-add';

describe('AttendanceAdd', () => {
  let component: AttendanceAdd;
  let fixture: ComponentFixture<AttendanceAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AttendanceAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(AttendanceAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
