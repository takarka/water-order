import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule, MatDividerModule, MatFormFieldModule, MatIconModule, MatOptionModule, MatRadioModule, MatSelectModule, MatSlideToggleModule } from '@angular/material';

import { FuseSidebarModule } from '../sidebar/sidebar.module';
import { FuseMaterialColorPickerModule } from '../material-color-picker/material-color-picker.module';

import { FuseThemeOptionsComponent } from './theme-options.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        FuseThemeOptionsComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,

        FlexLayoutModule,

        MatButtonModule,
        MatDividerModule,
        MatFormFieldModule,
        MatIconModule,
        MatOptionModule,
        MatRadioModule,
        MatSelectModule,
        MatSlideToggleModule,

        FuseMaterialColorPickerModule,
        FuseSidebarModule
    ],
    exports: [
        FuseThemeOptionsComponent
    ]
})
export class FuseThemeOptionsModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseThemeOptionsModule) {
        throwIfAlreadyLoaded(parentModule, 'FuseThemeOptionsModule');
    }
}
