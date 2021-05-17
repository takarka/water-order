import { NgModule, Optional, SkipSelf } from '@angular/core';

import { throwIfAlreadyLoaded } from '../shared/module-import.guard';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { MAT_DIALOG_DATA } from '@angular/material';


@NgModule({})
export class CoreModule {
    constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
        throwIfAlreadyLoaded(parentModule, 'CoreModule');
    }

    static forRoot(): ModuleWithProviders {
        return {
            ngModule: CoreModule,
            providers: [{ provide: MAT_DIALOG_DATA, useValue: {} }]
        };
    }
}
