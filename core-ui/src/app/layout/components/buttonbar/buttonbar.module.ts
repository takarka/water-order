import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FuseSharedModule } from '@fuse/shared.module';

import { ButtonBarComponent } from './buttonbar.component';
import { EmptyButtonsComponent } from './empty/empty-buttons.component';

@NgModule({
    declarations: [
        ButtonBarComponent,
        EmptyButtonsComponent
    ],
    imports: [
        RouterModule,
        FuseSharedModule  
    ],
    exports: [
        ButtonBarComponent,
        EmptyButtonsComponent
    ],
    entryComponents: [
        EmptyButtonsComponent
    ]
})
export class ButtonBarModule {
    constructor(@Optional() @SkipSelf() parentModule: ButtonBarModule) {
        // throwIfAlreadyLoaded(parentModule, 'FooterModule');
    }
}
