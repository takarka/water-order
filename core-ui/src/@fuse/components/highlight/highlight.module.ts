import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseHighlightComponent } from './highlight.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        FuseHighlightComponent
    ],
    exports: [
        FuseHighlightComponent
    ],
})
export class FuseHighlightModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseHighlightModule) {
        throwIfAlreadyLoaded(parentModule, 'FuseHighlightModule');
    }
}
