import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseNavigationModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';

import { NavbarHorizontalStyle1Component } from './style-1.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        NavbarHorizontalStyle1Component
    ],
    imports: [
        FuseSharedModule,
        FuseNavigationModule
    ],
    exports: [
        NavbarHorizontalStyle1Component
    ]
})
export class NavbarHorizontalStyle1Module {
    constructor(@Optional() @SkipSelf() parentModule: NavbarHorizontalStyle1Module) {
        throwIfAlreadyLoaded(parentModule, 'NavbarHorizontalStyle1Module');
    }
}
