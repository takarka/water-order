import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseSharedModule } from '@fuse/shared.module';

import { QuickPanelComponent } from './quick-panel.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        QuickPanelComponent
    ],
    imports: [
        FuseSharedModule,
    ],
    exports: [
        QuickPanelComponent
    ]
})
export class QuickPanelModule {
    constructor(@Optional() @SkipSelf() parentModule: QuickPanelModule) {
        throwIfAlreadyLoaded(parentModule, 'QuickPanelModule');
    }
}
