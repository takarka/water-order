import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { AuthenticantionGuard } from '@ms/core/auth/authentication.guard';
import { CoreDirectivesModule } from '@ms/core/directive/directives';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';
import { TranslateModule } from '@ngx-translate/core';
import { ButtonBarModule } from '../../layout/components/buttonbar/buttonbar.module';
import { OrdersButtonsComponent } from './buttons/buttons.component';
import { OrderComponent } from './order.component';
import { SimpleDialogTitleModule } from '@ms/core/components/dialog/simple-dialog/title/simple-dialog-title.module';

const routes = [
    {
        path: '',
        component: OrderComponent,
        canActivate: [AuthenticantionGuard]
    }
];


@NgModule({
    declarations: [OrderComponent, OrdersButtonsComponent],
    imports: [
        RouterModule.forChild(routes),
        TranslateModule,
        FuseSharedModule,
        ButtonBarModule,
        CoreDirectivesModule,
        SimpleDialogTitleModule
    ],
    exports: [OrderComponent, RouterModule, OrdersButtonsComponent],
    entryComponents: [
        OrdersButtonsComponent
    ]
})
export class OrderModule {
    constructor(@Optional() @SkipSelf() parentModule: OrderModule) {
        throwIfAlreadyLoaded(parentModule, 'OrderModule');
    }
}
