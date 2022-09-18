import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyHomesTableComponent } from './my-homes-table.component';

describe('MyHomesTableComponent', () => {
  let component: MyHomesTableComponent;
  let fixture: ComponentFixture<MyHomesTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyHomesTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyHomesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
