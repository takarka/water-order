import { NgModule, Optional, SkipSelf } from '@angular/core';
import { MaterialModule } from '../../../shared';
import { CommonModule } from '@angular/common';
import { SimpleDialogTitleModule } from './simple-dialog/title/simple-dialog-title.module';
import { SimpleDialogComponent } from './simple-dialog/simple-dialog.component';
import { MsDialogTitleDirective, MsDialogContentDirective, MsDialogActionsDirective } from './dialog.directive';
import { MsDialogComponent } from './dialog.component';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  declarations: [
    SimpleDialogComponent,
    MsDialogTitleDirective,
    MsDialogContentDirective,
    MsDialogActionsDirective,
    MsDialogComponent,
  ],
  imports: [CommonModule, MaterialModule, SimpleDialogTitleModule, TranslateModule],
  exports: [SimpleDialogComponent, SimpleDialogTitleModule],
  entryComponents: [SimpleDialogComponent],
})
export class CoreDialogModule {
  constructor(
    @Optional()
    @SkipSelf()
    parentModule: CoreDialogModule
  ) {
    // throwIfAlreadyLoaded(parentModule, 'FuseDirectivesModule');
  }
}
