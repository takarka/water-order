import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterExtensions } from 'nativescript-angular/router';
import { ActivatedRoute } from '@angular/router';
import { RegistrationForm, AuthService } from '../../auth.service';
import * as Toast from 'nativescript-toast';
import { RadDataFormComponent } from 'nativescript-ui-dataform/angular/dataform-directives';

@Component({
  selector: 'ns-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  moduleId: module.id,
})
export class RegistrationComponent implements OnInit {
  registrationData = new RegistrationForm();
  @ViewChild('registration') dataFormComponent: RadDataFormComponent;

  constructor(
    private service: AuthService,
    private routerExtension: RouterExtensions,
    private activeRoute: ActivatedRoute
  ) {}

  ngOnInit() {}
  register() {
    this.dataFormComponent.dataForm.validateAll().then(result => {
      console.log('Validation result: ', result);
      if (result) {
        this.service.register(this.registrationData).subscribe(
          result => {
            if (result.success) {
              Toast.makeText('Вы успешно зарегистрировались! Войдите используя свой логин и пароль', 'long').show();
              console.log('Successfully registered');
            }
            this.routerExtension.back({ relativeTo: this.activeRoute });
          },
          error => {
            console.log(error);
          }
        );
      }
    });
  }
  public onPropertyValidate(args) {
    let validationResult = true;
    if (args.propertyName === 'passwordVerify') {
      const dataForm = args.object;
      const password1 = dataForm.getPropertyByName('password');
      const passwordVerify = args.entityProperty;
      if (password1.valueCandidate !== passwordVerify.valueCandidate) {
        passwordVerify.errorMessage = 'Пароли не совпадают';
        validationResult = false;
      }
    }

    args.returnValue = validationResult;
  }
  goBack() {
    this.routerExtension.back({ relativeTo: this.activeRoute });
  }
}
