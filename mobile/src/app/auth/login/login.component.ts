import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService, LoginInfo } from './../../auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RouterExtensions } from 'nativescript-angular/router';
import { Page } from 'tns-core-modules/ui/page/page';
import { RadDataFormComponent } from 'nativescript-ui-dataform/angular/dataform-directives';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  returnUrl: any;
  processing: Boolean = false;
  loginForm: LoginInfo = new LoginInfo();
  @ViewChild('login') dataFormComponent: RadDataFormComponent;

  constructor(
    private route: ActivatedRoute,
    private routerExtension: RouterExtensions,
    private _page: Page,
    private authService: AuthService // {4}
  ) {
    this._page.actionBarHidden = true;
  }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || ['/'];
    console.log(this.returnUrl);
  }

  onGoogle() {
    this.authService.loginWithGoogle(this.returnUrl);
  }
  onSubmit() {
    this.dataFormComponent.dataForm.validateAll().then(result => {
      if (result) {
        this.processing = true;
        this.authService.login(this.loginForm).subscribe(
          () => {
            console.log('returnUrl: ', this.returnUrl);
            this.routerExtension.navigate(this.returnUrl, { clearHistory: true });
            // this.router.navigate(['/']);
          },
          error => {
            console.log('error');
            console.log(error);
            this.processing = false;
          },
          () => {
            console.log('done logging in!');
            this.processing = false;
          }
        );
      }
    });
  }
}
