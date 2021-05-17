import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { ProfileService, User } from '../services/profile.service';
import { RouterExtensions } from 'nativescript-angular/router';
import { ActivatedRoute } from '@angular/router';
import { BasketService } from '../services/basket.service';
import { Address, AddressForm } from '../services/order.service';
import { NavigationService } from '../navigation.service';

@Component({
  selector: 'ns-profile',
  moduleId: module.id,
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit, AfterViewInit {
  private user: User;
  addresses: Address[];

  constructor(
    private service: ProfileService,
    private authService: AuthService,
    private routerExtension: RouterExtensions,
    private basketService: BasketService,
    private navigationService: NavigationService
  ) {
    this.addresses = [];
  }

  ngOnInit() {
    console.log('PROFILE');
    if (!this.service.profile) {
      this.service.get().subscribe(result => {
        console.log(result);
        this.user = result;
      });
    }
    this.navigationService.tab.subscribe(index => {
      if(index === 0){
        this.basketService.getUserAddresses().subscribe(results => {
          this.addresses = results.body;
          console.log(this.addresses);
          console.log('ADDRESS COUNT', this.addresses.length);
        });
      }
    });
    
  }
  get profile() {
    return this.service.profile;
  }
  editProfile() {
    this.routerExtension.navigate(['/edit-profile']);
  }
  addAddress() {
    this.routerExtension.navigate(['address']);
  }
  editAddress(id: string) {
    this.routerExtension.navigate(['address', id]);
  }
  onItemTap(event) {
    console.log(event);
  }
  ngAfterViewInit(): void {
    console.log('PROFILE ngAfterViewInit');
  }
  logout() {
    this.authService.signOut();
  }
}
