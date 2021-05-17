import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FuseSidebarModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';

import { ContentModule } from '../../components/content/content.module';
import { FooterModule } from '../../components/footer/footer.module';
import { NavbarModule } from '../../components/navbar/navbar.module';
import { QuickPanelModule } from '../../components/quick-panel/quick-panel.module';
import { ToolbarModule } from '../../components/toolbar/toolbar.module';

import { VerticalLayout1Component } from './layout-1.component';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';

@NgModule({
    declarations: [
        VerticalLayout1Component
    ],
    imports: [
        RouterModule,

        FuseSharedModule,
        FuseSidebarModule,

        ContentModule,
        FooterModule,
        NavbarModule,
        QuickPanelModule,
        ToolbarModule
    ],
    exports: [
        VerticalLayout1Component,
        FooterModule
    ]
})
export class VerticalLayout1Module {
    constructor(@Optional() @SkipSelf() parentModule: VerticalLayout1Module) {
        throwIfAlreadyLoaded(parentModule, 'VerticalLayout1Module');
    }
}
