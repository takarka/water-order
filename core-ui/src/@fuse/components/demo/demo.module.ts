import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MatDividerModule, MatListModule } from '@angular/material';

import { FuseDemoContentComponent } from './demo-content/demo-content.component';
import { FuseDemoSidebarComponent } from './demo-sidebar/demo-sidebar.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        FuseDemoContentComponent,
        FuseDemoSidebarComponent
    ],
    imports: [
        RouterModule,

        MatDividerModule,
        MatListModule
    ],
    exports: [
        FuseDemoContentComponent,
        FuseDemoSidebarComponent
    ]
})
export class FuseDemoModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseDemoModule) {
        throwIfAlreadyLoaded(parentModule, 'FuseDemoModule');
    }
}
