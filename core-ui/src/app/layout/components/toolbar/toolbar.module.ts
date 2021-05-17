import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FuseSearchBarModule, FuseShortcutsModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';

import { ToolbarComponent } from './toolbar.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        ToolbarComponent
    ],
    imports: [
        RouterModule,
        FuseSharedModule,
        FuseSearchBarModule,
        FuseShortcutsModule
    ],
    exports: [
        ToolbarComponent
    ]
})
export class ToolbarModule {
    constructor(@Optional() @SkipSelf() parentModule: ToolbarModule) {
        throwIfAlreadyLoaded(parentModule, 'ToolbarModule');
    }
}
