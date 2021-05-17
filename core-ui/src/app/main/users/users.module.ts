import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { AuthenticantionGuard } from '@ms/core/auth/authentication.guard';
import { CoreDirectivesModule } from '@ms/core/directive/directives';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';
import { TranslateModule } from '@ngx-translate/core';
import { ButtonBarModule } from '../../layout/components/buttonbar/buttonbar.module';
import { UsersComponent } from './users.component';
import { UserDialogComponent } from './dialog/user-dialog.component';
import { UserFormButtonsComponent } from './user-form/buttons/buttons.component';
import { UserFormComponent } from './user-form/user-form.component';
import { SimpleDialogTitleModule } from '@ms/core/components/dialog/simple-dialog/title/simple-dialog-title.module';

const routes = [
    {
        path: '',
        component: UsersComponent,
        canActivate: [AuthenticantionGuard]
    },
    {
        path: 'info',
        component: UserFormComponent,
        canActivate: [AuthenticantionGuard]
    },
    {
        path: 'info/:id',
        component: UserFormComponent,
        canActivate: [AuthenticantionGuard]
    }
];


@NgModule({
    declarations: [
        UsersComponent,
        UserFormComponent,
        UserDialogComponent,
        UserFormButtonsComponent
    ],
    imports: [
        RouterModule.forChild(routes),
        TranslateModule,
        FuseSharedModule,
        ButtonBarModule,
        CoreDirectivesModule,
        SimpleDialogTitleModule
    ],
    exports: [UsersComponent, UserFormButtonsComponent, RouterModule],
    entryComponents: [
        UserDialogComponent,
        UserFormButtonsComponent
    ]
})
export class UsersModule {
    constructor(@Optional() @SkipSelf() parentModule: UsersModule) {
        throwIfAlreadyLoaded(parentModule, 'UsersModule');
    }
}
