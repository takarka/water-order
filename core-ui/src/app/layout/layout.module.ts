import { NgModule, Optional, SkipSelf } from '@angular/core';

import { VerticalLayout1Module } from './vertical/layout-1/layout-1.module';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    imports: [
        VerticalLayout1Module,
        // VerticalLayout2Module,
        // VerticalLayout3Module,
        // HorizontalLayout1Module
    ],
    exports: [
        VerticalLayout1Module,
        // VerticalLayout2Module,
        // VerticalLayout3Module,

        // HorizontalLayout1Module    
    ]
})
export class LayoutModule {
    constructor(@Optional() @SkipSelf() parentModule: LayoutModule) {
        throwIfAlreadyLoaded(parentModule, 'LayoutModule');
    }
}
