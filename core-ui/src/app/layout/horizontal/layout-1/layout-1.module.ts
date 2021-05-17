import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseSidebarModule, FuseThemeOptionsModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';

import { ContentModule } from '../../components/content/content.module';
import { FooterModule } from '../../components/footer/footer.module';
import { NavbarModule } from '../../components/navbar/navbar.module';
import { QuickPanelModule } from '../../components/quick-panel/quick-panel.module';
import { ToolbarModule } from '../../components/toolbar/toolbar.module';

import { HorizontalLayout1Component } from './layout-1.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        HorizontalLayout1Component
    ],
    imports: [
        FuseSharedModule,
        FuseSidebarModule,
        FuseThemeOptionsModule,

        ContentModule,
        FooterModule,
        NavbarModule,
        QuickPanelModule,
        ToolbarModule
    ],
    exports: [
        HorizontalLayout1Component
    ]
})
export class HorizontalLayout1Module {
    constructor(@Optional() @SkipSelf() parentModule: HorizontalLayout1Module) {
        throwIfAlreadyLoaded(parentModule, 'HorizontalLayout1Module');
    }
}
