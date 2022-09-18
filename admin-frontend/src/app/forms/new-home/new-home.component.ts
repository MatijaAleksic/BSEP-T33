import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { HomeService } from 'src/app/service/home.service';


interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-new-home',
  templateUrl: './new-home.component.html',
  styleUrls: ['./new-home.component.css']
})
export class NewHomeComponent implements OnInit {

  form: FormGroup;
  submitted = false;
  notification: DisplayMessage;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  roles: string[] = ["ROLE_USER", "ROLE_ADMIN"]

  constructor(
    private homeService: HomeService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.form = this.formBuilder.group({
      name: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {

    this.notification = undefined;
    this.submitted = true;

    let send = { "id" : 0, "name" : this.form.value.name}

    this.homeService.newHome(send)
        .subscribe(data => {
          console.log(data);
          this.router.navigate(['admin-homes-table']);
        
        },
          error => {
            this.submitted = false;
            this.notification = { msgType: 'error', msgBody: error['error'].message };
            this.router.navigate(['admin-homes-table']);
          });
  }

}
