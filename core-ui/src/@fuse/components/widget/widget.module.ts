import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseWidgetComponent } from './widget.component';
import { FuseWidgetToggleDirective } from './widget-toggle.directive';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        FuseWidgetComponent,
        FuseWidgetToggleDirective
    ],
    exports: [
        FuseWidgetComponent,
        FuseWidgetToggleDirective
    ],
})
export class FuseWidgetModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseWidgetModule) {
        throwIfAlreadyLoaded(parentModule, 'FuseWidgetModule');
    }
}
