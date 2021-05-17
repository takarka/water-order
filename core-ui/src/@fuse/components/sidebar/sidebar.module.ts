import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseSidebarComponent } from './sidebar.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        FuseSidebarComponent
    ],
    exports: [
        FuseSidebarComponent
    ]
})
export class FuseSidebarModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseSidebarModule) {
        throwIfAlreadyLoaded(parentModule, 'FuseSidebarModule');
    }
}
