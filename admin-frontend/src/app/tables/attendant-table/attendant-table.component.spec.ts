import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendantTableComponent } from './attendant-table.component';

describe('AttendantTableComponent', () => {
  let component: AttendantTableComponent;
  let fixture: ComponentFixture<AttendantTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttendantTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendantTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
