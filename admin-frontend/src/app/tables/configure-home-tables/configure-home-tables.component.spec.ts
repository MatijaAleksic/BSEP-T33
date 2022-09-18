import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigureHomeTablesComponent } from './configure-home-tables.component';

describe('ConfigureHomeTablesComponent', () => {
  let component: ConfigureHomeTablesComponent;
  let fixture: ComponentFixture<ConfigureHomeTablesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfigureHomeTablesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigureHomeTablesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
