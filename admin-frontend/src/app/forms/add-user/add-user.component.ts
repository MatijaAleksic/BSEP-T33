import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';
import { UserService } from 'src/app/service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})

export class AddUserComponent implements OnInit {

  form: FormGroup;
  /**
   * Boolean used in telling the UI
   * that the form has been submitted
   * and is awaiting a response
   */
  submitted = false;
  /**
   * Notification message from received
   * form request or router
   */
  notification: DisplayMessage;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  roles: string[] = ["ROLE_USER", "ROLE_ADMIN"]

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      firstName: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      lastName: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      email: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32), Validators.email])],
      role: ['',Validators.compose([Validators.required])]
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

    if(this.form.value.role === "ROLE_ADMIN"){
      this.userService.addNewAdmin(this.form.value)
        .subscribe(data => {
          //this.router.navigate([this.returnUrl]);
          this.router.navigate(['user-table']);
        
        },
          error => {
            this.submitted = false;
            this.notification = { msgType: 'error', msgBody: error['error'].message };
            this.router.navigate(['user-table']);
          });
    }
    else{
      this.userService.addNewUser(this.form.value)
        .subscribe(data => {
          this.router.navigate(['user-table']);
        },
          error => {
            this.submitted = false;
            this.notification = { msgType: 'error', msgBody: error['error'].message };
          });
    }

  }

}
