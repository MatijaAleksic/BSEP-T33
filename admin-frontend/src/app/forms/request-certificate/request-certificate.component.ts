import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CertificaterequestService } from 'src/app/service/certificaterequest.service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-request-certificate',
  templateUrl: './request-certificate.component.html',
  styleUrls: ['./request-certificate.component.css']
})
export class RequestCertificateComponent implements OnInit {

  form: FormGroup;
  submitted = false;
  notification: DisplayMessage;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  //extensions: {"crt" : string, "cer": string, "pem": string, "der": string}
  extensions : any;

  constructor(
    private certificateRequestService: CertificaterequestService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,

  ) {
    this.extensions = ["crt", "cer", "pem", "der"]
   }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      commonName: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      surname: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      givenName: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      organization: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      organizationUnit: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      country: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      email: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32), Validators.email])],
      certExtension: ['',Validators.compose([Validators.required])],
    });
  }

  onSubmit() {
    /**
     * Innocent until proven guilty
     */
    this.notification = undefined;
    this.submitted = true;

    console.log(this.form.value);

    this.certificateRequestService.create(this.form.value)
    .subscribe(data => {
          this.router.navigate(['/']);
        },
          error => {
            this.submitted = false;
            this.notification = { msgType: 'error', msgBody: error['error'].message };
          });

  }

  // setSelection(option: string){
  //   this.form.controls['extension'].setValue(option);
  // }

}
