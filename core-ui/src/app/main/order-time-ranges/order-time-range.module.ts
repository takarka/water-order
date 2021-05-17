import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { AuthenticantionGuard } from '@ms/core/auth/authentication.guard';
import { CoreDirectivesModule } from '@ms/core/directive/directives';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';
import { TranslateModule } from '@ngx-translate/core';
import { ButtonBarModule } from '../../layout/components/buttonbar/buttonbar.module';
import { OrderTimeRangeComponent } from './order-time-range.component';
import { TimeRangeDialogComponent } from './dialog/time-range-dialog.component';
import { SimpleDialogTitleModule } from '@ms/core/components/dialog/simple-dialog/title/simple-dialog-title.module';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';

const routes = [
    {
        path: '',
        component: OrderTimeRangeComponent,
        canActivate: [AuthenticantionGuard]
    }
];


@NgModule({
    declarations: [OrderTimeRangeComponent, TimeRangeDialogComponent],
    imports: [
        RouterModule.forChild(routes),
        TranslateModule,
        FuseSharedModule,
        ButtonBarModule,
        CoreDirectivesModule,
        SimpleDialogTitleModule,
        NgxMaterialTimepickerModule
    ],
    exports: [OrderTimeRangeComponent, RouterModule],
    entryComponents: [
        TimeRangeDialogComponent
    ]
})
export class OrderTimeRangeModule {
    constructor(@Optional() @SkipSelf() parentModule: OrderTimeRangeModule) {
        throwIfAlreadyLoaded(parentModule, 'OrderTimeRangeModule');
    }
}
