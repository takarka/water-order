import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FuseSharedModule } from '@fuse/shared.module';

import { FooterComponent } from './footer.component';
import { ButtonBarModule } from '../buttonbar/buttonbar.module';

@NgModule({
    declarations: [
        FooterComponent
    ],
    imports: [
        RouterModule,
        FuseSharedModule,
        ButtonBarModule  
    ],
    exports: [
        FooterComponent
    ]
})
export class FooterModule {
    constructor(@Optional() @SkipSelf() parentModule: FooterModule) {
        // throwIfAlreadyLoaded(parentModule, 'FooterModule');
    }
}
