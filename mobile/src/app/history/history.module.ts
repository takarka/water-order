import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { Routes } from '@angular/router';
import { NativeScriptRouterModule } from 'nativescript-angular/router';
import { HistoryComponent } from './history.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';
const routes: Routes = [
  { path: '', redirectTo: 'order-history' },
  { path: 'order-history', component: HistoryComponent },
];
@NgModule({
  declarations: [HistoryComponent],
  imports: [NativeScriptCommonModule, NativeScriptRouterModule.forChild(routes)],
  exports: [NativeScriptRouterModule],
  schemas: [NO_ERRORS_SCHEMA],
})
export class HistoryModule { }
