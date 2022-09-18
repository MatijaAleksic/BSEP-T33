import { Component, OnInit, Input} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CertificateService } from 'src/app/service/certificate.service';



interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-revoke-certificate',
  templateUrl: './revoke-certificate.component.html',
  styleUrls: ['./revoke-certificate.component.css']
})
export class RevokeCertificateComponent implements OnInit {

  email : string;

  form: FormGroup;
  submitted = false;
  notification: DisplayMessage;


  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  reasons: string[] = ["KEY_COMPROMISE", "CA_COMPROMISE", "CERTIFICATE_HOLD", "AA_COMPROMISE", "AFFILIATION_CHANGED", "CESSATION_OF_OPERATION",
  "PRIVILEGE_WITHDRAWN", "REMOVE_FROM_CRL", "SUPERSEDED", "UNSPECIFIED", "UNUSED"]

  constructor(
    private certificateService: CertificateService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.email = this.certificateService.getRevokeEmail();

    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      revocationReason: ['', Validators.compose([Validators.required])],
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {
    /**
     * Innocent until proven guilty
     */
    this.notification = undefined;
    this.submitted = true;

    let dto = {subjectAlias: this.email , revocationReason : this.form.value.revocationReason}

    this.certificateService.revoke(dto)
    .subscribe(data => {
          this.router.navigate(['/certificate-table']);
        },
          error => {
            this.submitted = false;
            this.notification = { msgType: 'error', msgBody: error['error'].message };
          });

  }

}
