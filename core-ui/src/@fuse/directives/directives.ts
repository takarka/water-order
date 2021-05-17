import { NgModule, Optional, SkipSelf } from '@angular/core';

import { FuseIfOnDomDirective } from './fuse-if-on-dom/fuse-if-on-dom.directive';
import { FuseInnerScrollDirective } from './fuse-inner-scroll/fuse-inner-scroll.directive';
import { FusePerfectScrollbarDirective } from './fuse-perfect-scrollbar/fuse-perfect-scrollbar.directive';
import { FuseMatSidenavHelperDirective, FuseMatSidenavTogglerDirective } from './fuse-mat-sidenav/fuse-mat-sidenav.directive';

@NgModule({
    declarations: [
        FuseIfOnDomDirective,
        FuseInnerScrollDirective,
        FuseMatSidenavHelperDirective,
        FuseMatSidenavTogglerDirective,
        FusePerfectScrollbarDirective
    ],
    imports: [],
    exports: [
        FuseIfOnDomDirective,
        FuseInnerScrollDirective,
        FuseMatSidenavHelperDirective,
        FuseMatSidenavTogglerDirective,
        FusePerfectScrollbarDirective
    ]
})
export class FuseDirectivesModule {
    constructor(@Optional() @SkipSelf() parentModule: FuseDirectivesModule) {
        // throwIfAlreadyLoaded(parentModule, 'FuseDirectivesModule');
    }
}
