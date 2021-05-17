import { NgModule, Optional, SkipSelf } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';
import { AuthenticantionGuard } from '@ms/core/auth/authentication.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
    canLoad: [AuthenticantionGuard],
  },
  {
    path: 'order-time-range',
    loadChildren: 'app/main/order-time-ranges/order-time-range.module#OrderTimeRangeModule',
    canLoad: [AuthenticantionGuard],
  },
  {
    path: 'orders',
    loadChildren: 'app/main/orders/order.module#OrderModule',
    canLoad: [AuthenticantionGuard],
  },
  {
    path: 'product-category',
    loadChildren: 'app/main/product-category/product-category.module#ProductCategoryModule',
    canLoad: [AuthenticantionGuard],
  },
  {
    path: 'user',
    loadChildren: 'app/main/users/users.module#UsersModule',
    canLoad: [AuthenticantionGuard],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
  constructor(
    @Optional()
    @SkipSelf()
    parentModule: AppRoutingModule
  ) {
    throwIfAlreadyLoaded(parentModule, 'AppRoutingModule');
  }
}
