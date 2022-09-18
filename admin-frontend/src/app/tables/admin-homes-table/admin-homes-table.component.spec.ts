import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHomesTableComponent } from './admin-homes-table.component';

describe('AdminHomesTableComponent', () => {
  let component: AdminHomesTableComponent;
  let fixture: ComponentFixture<AdminHomesTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminHomesTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminHomesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
