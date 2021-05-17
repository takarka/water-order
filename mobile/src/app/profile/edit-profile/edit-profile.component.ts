import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterExtensions } from 'nativescript-angular/router';
import { ActivatedRoute } from '@angular/router';
import * as Toast from 'nativescript-toast';
import { RadDataFormComponent } from 'nativescript-ui-dataform/angular/dataform-directives';
import { ProfileService, EditProfileForm } from '~/app/services/profile.service';

@Component({
  selector: 'ns-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
  moduleId: module.id,
})
export class EditProfileComponent implements OnInit {
  profileData: EditProfileForm;
  @ViewChild('profile') dataFormComponent: RadDataFormComponent;

  constructor(
    private service: ProfileService,
    private routerExtension: RouterExtensions,
    private activeRoute: ActivatedRoute
  ) {}

  ngOnInit() {
      this.profileData = new EditProfileForm(this.service.profile.name, this.service.profile.phone);
  }
  doEditProfile() {
    this.dataFormComponent.dataForm.validateAll().then(result => {
      console.log('Validation result: ', result);
      if (result) {
        this.service.editProfile(this.profileData).subscribe(
          result => {
            if (result.success) {
              Toast.makeText('Изменения успешно сохранены').show();
              console.log('Successfully edited profile');
            }
            this.routerExtension.back({ relativeTo: this.activeRoute });
          },
          error => {
            Toast.makeText('Ошибка! Попробуйте еще раз').show();
            console.log(error);
          }
        );
      }
    });
  }
  goBack() {
    this.routerExtension.back({ relativeTo: this.activeRoute });
  }
}
