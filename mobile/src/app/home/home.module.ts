import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { Routes } from '@angular/router';
import { NativeScriptRouterModule } from 'nativescript-angular/router';
import { HomeComponent } from './home.component';
import { NativeScriptFormsModule } from 'nativescript-angular/forms';
const routes: Routes = [
  { path: "", redirectTo: "home" },
  { path: "home", component: HomeComponent }
]
@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    NativeScriptCommonModule,
    NativeScriptFormsModule,
    NativeScriptRouterModule.forChild(routes)
  ],
  exports: [NativeScriptRouterModule],
  schemas: [NO_ERRORS_SCHEMA]
})
export class HomeModule { }
