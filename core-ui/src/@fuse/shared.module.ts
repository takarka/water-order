import { NgModule, Optional, SkipSelf, ModuleWithProviders } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { FlexLayoutModule } from '@angular/flex-layout';

import { FuseDirectivesModule } from './directives/directives';
import { FusePipesModule } from './pipes/pipes.module';
import { MaterialModule } from '@ms/shared/material.module';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

const sharedModules: any[] = [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    FuseDirectivesModule,
    FusePipesModule,
    MaterialModule
];
@NgModule({
    imports: sharedModules,
    exports: sharedModules
})
export class FuseSharedModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseSharedModule) {
        // throwIfAlreadyLoaded(parentModule, 'FuseSharedModule');
    }
}
