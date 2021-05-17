import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NSEmptyOutletComponent, NativeScriptRouterModule } from "nativescript-angular/router";
import { Routes } from '@angular/router';
import { TabsComponent } from './tabs.component';
const routes: Routes = [
  {
    path: "default", component: TabsComponent, children: [
        {
            path: "profile",
            outlet: "profileTab",
            component: NSEmptyOutletComponent,
            loadChildren: "~/app/profile/profile.module#ProfileModule",
        },
        {
            path: "home",
            outlet: "homeTab",
            component: NSEmptyOutletComponent,
            loadChildren: "~/app/home/home.module#HomeModule"
        },
        {
            path: "history",
            outlet: "historyTab",
            component: NSEmptyOutletComponent,
            loadChildren: "~/app/history/history.module#HistoryModule"
        }
    ]
 }
]
@NgModule({
  declarations: [
    TabsComponent
  ],
  imports: [
    NativeScriptCommonModule,
    NativeScriptRouterModule,
    NativeScriptRouterModule.forChild(routes)
  ],
  providers: [
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class TabsModule { }
