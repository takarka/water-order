import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseNavigationModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';

import { NavbarVerticalStyle1Component } from './style-1.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        NavbarVerticalStyle1Component
    ],
    imports: [
        FuseSharedModule,
        FuseNavigationModule
    ],
    exports: [
        NavbarVerticalStyle1Component
    ]
})
export class NavbarVerticalStyle1Module {
    constructor(@Optional() @SkipSelf() parentModule: NavbarVerticalStyle1Module) {
        throwIfAlreadyLoaded(parentModule, 'NavbarVerticalStyle1Module');
    }
}
