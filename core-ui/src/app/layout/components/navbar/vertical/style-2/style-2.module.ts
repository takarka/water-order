import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseNavigationModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';

import { NavbarVerticalStyle2Component } from './style-2.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        NavbarVerticalStyle2Component
    ],
    imports: [
        FuseSharedModule,
        FuseNavigationModule
    ],
    exports: [
        NavbarVerticalStyle2Component
    ]
})
export class NavbarVerticalStyle2Module {
    constructor(@Optional() @SkipSelf() parentModule: NavbarVerticalStyle2Module) {
        throwIfAlreadyLoaded(parentModule, 'NavbarVerticalStyle2Module');
    }
}
