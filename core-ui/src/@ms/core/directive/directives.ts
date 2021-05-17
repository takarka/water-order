import { NgModule, Optional, SkipSelf } from '@angular/core';
import { ConfirmDirective } from './confirm.directive';
import { MaterialModule } from '../../shared';
import { IfShowDirective } from './ifShow.directive';
import { CoreDialogModule } from '../components/dialog/core-dialog.module';
@NgModule({
    declarations: [
        ConfirmDirective, IfShowDirective
    ],
    imports: [CoreDialogModule, MaterialModule],
    exports: [
        ConfirmDirective, IfShowDirective
    ]
})
export class CoreDirectivesModule {
    constructor(@Optional() @SkipSelf() parentModule: CoreDirectivesModule) {
    }
}
