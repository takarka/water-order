import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterExtensions } from 'nativescript-angular/router';
import { ActivatedRoute } from '@angular/router';
import * as Toast from 'nativescript-toast';
import { RadDataFormComponent } from 'nativescript-ui-dataform/angular/dataform-directives';
import { ProfileService } from '~/app/services/profile.service';
import { AddressForm, Address, City, Region } from '~/app/services/order.service';
import { NavigationService } from '~/app/navigation.service';

@Component({
  selector: 'ns-edit-address',
  templateUrl: './edit-address.component.html',
  styleUrls: ['./edit-address.component.css'],
  moduleId: module.id,
})
export class EditAddressComponent implements OnInit {
  addressForm: AddressForm;
  public regions: any;
  public cities: any;
  id: string;

  @ViewChild('address') dataFormComponent: RadDataFormComponent;

  constructor(
    private service: ProfileService,
    private routerExtension: RouterExtensions,
    private activeRoute: ActivatedRoute,
    private navigationService: NavigationService
  ) {
    this.addressForm = new AddressForm();
  }

  ngOnInit() {
    this.activeRoute.params.subscribe(paramsId => {
      console.log(paramsId);
      this.id = paramsId.id;
      if (this.id && this.id !== '') {
        this.service.getAddress(this.id).subscribe(result => {
          this.addressForm = new AddressForm(
            result.name,
            result.city ? result.city.id : '',
            result.region ? result.region.id : '',
            result.street,
            result.buildingNumber,
            result.floor,
            result.entranceCode,
            result.flatNumber
          );
        });
      }
    });
    this.service.getRegions().subscribe(result => {
      this.regions = {
        key: 'id',
        label: 'name',
        items: result,
      };
    });
    this.service.getCities().subscribe(result => {
      this.cities = {
        key: 'id',
        label: 'name',
        items: result,
      };
    });
  }
  doSave() {
    this.dataFormComponent.dataForm.validateAll().then(result => {
      if (result) {
        console.log(this.addressForm);
        let address = new Address();
        address.name = this.addressForm.name;
        address.city = new City(this.addressForm.city);
        address.region = new Region(this.addressForm.region);
        address.street = this.addressForm.street;
        address.buildingNumber = this.addressForm.buildingNumber;
        address.floor = this.addressForm.floor;
        address.entranceCode = this.addressForm.entranceCode;
        address.flatNumber = this.addressForm.flatNumber;
        if (this.id && this.id !== '') {
          address.id = this.id;
          this.service.updateAddress(address).subscribe(
            result => {
              Toast.makeText('Изменения успешно сохранены').show();
              this.goBack();
            },
            error => {
              Toast.makeText('Ошибка! Попробуйте еще раз').show();
              console.log(error);
            }
          );
        } else {
          this.service.saveAddress(address).subscribe(
            result => {
              Toast.makeText('Изменения успешно сохранены').show();
              this.goBack();
            },
            error => {
              Toast.makeText('Ошибка! Попробуйте еще раз').show();
              console.log(error);
            }
          );
        }
      }
    });
  }
  doDelete() {
    if (this.id && this.id !== '') {
      this.service.deleteAddress(this.id).subscribe(
        result => {
          Toast.makeText('Адрес успешно удален').show();
          this.goBack();
        },
        error => {
          Toast.makeText('Ошибка! Попробуйте еще раз').show();
          console.log(error);
        }
      );
    }
  }
  goBack() {
    this.navigationService.changeTab(0);
    this.routerExtension.back({ relativeTo: this.activeRoute });
  }
}
