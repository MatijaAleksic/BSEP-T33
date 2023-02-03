import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Subject} from 'rxjs/Subject';
import {takeUntil} from 'rxjs/operators';
import { AuthService, UserService } from 'src/app/service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title = 'Login';
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

    user : any;

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit() {
    // this.route.params
    //   .pipe(takeUntil(this.ngUnsubscribe))
    //   .subscribe((params: DisplayMessage) => {
    //     this.notification = params;
    //   });
    // // get return url from route parameters or default to '/'
    // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(20)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(20)])]
    });
  }

  ngOnDestroy() {
    // this.ngUnsubscribe.next();
    // this.ngUnsubscribe.complete();
  }

  onSubmit() {
    this.notification = undefined;
    this.submitted = true;

    this.authService.login(this.form.value)
      .subscribe(data => {
          this.router.navigate(['/']);
        },
        error => {
          this.submitted = false;
          this.notification = {msgType: 'error', msgBody: 'Incorrect username or password.'};
        });
  }
}
