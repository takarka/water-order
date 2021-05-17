import { NgModule } from '@angular/core';
import { NativeScriptRouterModule } from 'nativescript-angular/router';
import { Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { BasketComponent } from './basket/basket.component';
import { RegistrationComponent } from './auth/login/registration/registration.component';
import { EditProfileComponent } from './profile/edit-profile/edit-profile.component';
import { EditAddressComponent } from './profile/edit-address/edit-address.component';
import { OrderDetailComponent } from './history/order-detail/order-detail.component';
const routes: Routes = [
  {
    path: '',
    redirectTo: '/tabs/default',
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'tabs',
    redirectTo: '/tabs/default',
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'home',
    redirectTo: '/tabs/default',
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  { path: 'address', component: EditAddressComponent },
  { path: 'address/:id', component: EditAddressComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'edit-profile', component: EditProfileComponent },
  {
    path: 'tabs',
    loadChildren: '~/app/tabs/tabs.module#TabsModule',
    canActivate: [AuthGuard],
  },
  { path: 'basket', component: BasketComponent, canActivate: [AuthGuard] },
  { path: 'order-detail', component: OrderDetailComponent },
];

@NgModule({
  imports: [NativeScriptRouterModule.forRoot(routes)],
  exports: [NativeScriptRouterModule],
})
export class AppRoutingModule {}
