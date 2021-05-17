import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NativeScriptRouterModule } from 'nativescript-angular/router';
import { Routes } from '@angular/router';
import { ProfileComponent } from './profile.component';
import { BasketService } from '../services/basket.service';
const routes: Routes = [{ path: '', redirectTo: 'profile' }, { path: 'profile', component: ProfileComponent }];
@NgModule({
  declarations: [ProfileComponent],
  imports: [NativeScriptCommonModule, NativeScriptRouterModule.forChild(routes)],
  exports: [NativeScriptRouterModule],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [BasketService],
})
export class ProfileModule {}
