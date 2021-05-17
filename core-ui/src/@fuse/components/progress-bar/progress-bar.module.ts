import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MatButtonModule, MatIconModule, MatProgressBarModule } from '@angular/material';

import { FuseProgressBarComponent } from './progress-bar.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        FuseProgressBarComponent
    ],
    imports: [
        CommonModule,
        RouterModule,

        MatButtonModule,
        MatIconModule,
        MatProgressBarModule
    ],
    exports: [
        FuseProgressBarComponent
    ]
})
export class FuseProgressBarModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseProgressBarModule) {
        throwIfAlreadyLoaded(parentModule, 'FuseProgressBarModule');
    }

}
