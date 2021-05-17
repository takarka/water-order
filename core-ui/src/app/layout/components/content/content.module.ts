import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FuseSharedModule } from '@fuse/shared.module';

import { ContentComponent } from './content.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        ContentComponent
    ],
    imports: [
        RouterModule,
        FuseSharedModule,
    ],
    exports: [
        ContentComponent
    ]
})
export class ContentModule {
    constructor(@Optional() @SkipSelf() parentModule: ContentModule) {
        throwIfAlreadyLoaded(parentModule, 'ContentModule');
    }
}
