import { AfterViewChecked, ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { ButtonsBarService, ViewState } from '@ms/core';
import { DialogService } from '@ms/core/components/dialog/service/dialog.service';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from 'app/main/service/user.service';
import { AuthProvider, RoleEnum, User } from 'app/main/shared/model/user.model';
import { user_module_path } from 'app/main/shared/module';
import { Subscription } from 'rxjs';
import { UserFormButtonsComponent } from './buttons/buttons.component';
import { UserUIService } from './user-ui.service';

@Component({
  selector: 'user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss'],
})
export class UserFormComponent implements OnInit, OnDestroy, AfterViewChecked {
  newButton: Subscription;
  saveButton: Subscription;
  deleteButton: Subscription;

  userForm: FormGroup;
  user: User;
  id: string;
  state: string;
  RoleEnum = RoleEnum;
  roleKeys = Object.keys(RoleEnum);

  constructor(
    private buttonBarService: ButtonsBarService,
    private service: UserService,
    private serviceUI: UserUIService,
    private _dialogService: DialogService,
    public snackBar: MatSnackBar,
    private changeDetector: ChangeDetectorRef,
    private _formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private translate: TranslateService
  ) {
    this.user = new User();
    this.createForm();
  }

  ngOnInit(): void {
    this.buttonBarService.addContent({
      path: user_module_path,
      component: UserFormButtonsComponent,
    });
    this.newButton = this.serviceUI.new.subscribe(value => this.doNew(value));
    this.saveButton = this.serviceUI.save.subscribe(value => this.doSave(value));
    this.deleteButton = this.serviceUI.delete.subscribe(value => this.doDelete(value));
    this.id = this.route.snapshot.paramMap.get('id');
    this.route.params.subscribe(paramsId => (this.id = paramsId.id));
    const isEdit = this.id && this.id !== null;
    this.state = isEdit ? 'edit' : 'new';
    if (isEdit) {
      this.service.getById(this.id).subscribe(response => {
        this.user = response.body;
        this.fillForm();
      });
    }
  }

  fillForm() {
    this.userForm.patchValue(this.user);
  }

  createForm() {
    this.userForm = this._formBuilder.group({
      id: [null],
      name: [null, [Validators.required, Validators.min(3)]],
      email: ['', [Validators.email]],
      phone: [''],
      active: [true],
      provider: [AuthProvider.local],
      roles: [null],
      //   contactInfo: this._formBuilder.group({
      //     phone: [''],
      //     web: [''],
      //     fax: [''],
      //     email: ['', [Validators.email]],
      //   }),
      //   address: this._formBuilder.group({
      //     country: this._formBuilder.group({
      //       id: [null, Validators.required],
      //     }),
      //     city: this._formBuilder.group({
      //       id: [null],
      //     }),
      //     postalCode: [''],
      //     street: [''],
      //   }),
      //   translateName: [[]],
      //   mailServer: this._formBuilder.group({
      //     port: [null, [Validators.min(0), Validators.max(65535)]],
      //     username: [null, Validators.required],
      //     password: [null, Validators.required],
      //     enabled: [false],
      //     smtpAuth: [true],
      //     startTlsRequired: [true],
      //     startTlsEnabled: [true],
      //   }),
      //   timeZone: [''],
      //   currency: [''],
      //   phoneMask: [null, [Validators.required]],
      //   language: [null, [Validators.required]],
    });
  }

  doNew(event: Event) {
    this.userForm.reset();
    this.state = 'new';
  }

  doSave(event: Event) {
    if (!this.userForm.valid) {
      this._dialogService.error(this.translate.instant('GENERAL.MESSAGE.ERROR.FORM_INVALID'));
      return;
    }
    const data = this.userForm.value;
    if (data.id) {
      this.service.update(data).subscribe(response => {
        this.user = response.body;
        this._dialogService.success(this.translate.instant('GENERAL.MESSAGE.SUCCESS.SAVED_SUCCESSFULLY'));
      });
    } else {
      this.service.create(data).subscribe(response => {
        this.user = response.body;
        this._dialogService.success(this.translate.instant('GENERAL.MESSAGE.SUCCESS.SAVED_SUCCESSFULLY'));
      });
    }
  }

  doDelete(event: Event) {
    const data = this.userForm.value;
    if (data.id) {
      this.service.delete(data.id).subscribe(
        result => {
          const item = this.translate.instant('GENERAL.FIELD.SCHOOL');
          const message = this.translate.instant('GENERAL.MESSAGE.SUCCESS.DELETED', { item: item });
          this._dialogService.success(message);
          this.doNew(event);
        },
        err => {
          // TODO maybe use err.error.errorKey
          this._dialogService.error(err.error.messageDetail);
        }
      );
    }
  }

  ngAfterViewChecked() {
    if (this.state === 'edit') {
      this.serviceUI.setState(ViewState.EDIT);
    }
    this.changeDetector.detectChanges();
  }

  /**
   * On destroy
   */
  ngOnDestroy(): void {
    console.log('user-form component destroyed !');
    this.changeDetector.detach();
    this.newButton.unsubscribe();
    this.saveButton.unsubscribe();
    this.deleteButton.unsubscribe();
  }
}
