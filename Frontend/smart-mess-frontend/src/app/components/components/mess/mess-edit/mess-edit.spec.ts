import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessEdit } from './mess-edit';

describe('MessEdit', () => {
  let component: MessEdit;
  let fixture: ComponentFixture<MessEdit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MessEdit],
    }).compileComponents();

    fixture = TestBed.createComponent(MessEdit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
