import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CertificateRequestTableComponent } from './certificate-request-table.component';

describe('CertificateRequestTableComponent', () => {
  let component: CertificateRequestTableComponent;
  let fixture: ComponentFixture<CertificateRequestTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CertificateRequestTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CertificateRequestTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
