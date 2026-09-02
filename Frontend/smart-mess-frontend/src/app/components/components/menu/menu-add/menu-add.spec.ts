import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuAdd } from './menu-add';

describe('MenuAdd', () => {
  let component: MenuAdd;
  let fixture: ComponentFixture<MenuAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuAdd],
    }).compileComponents();

    fixture = TestBed.createComponent(MenuAdd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
