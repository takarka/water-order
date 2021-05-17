import { NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { SimpleDialogTitleComponent } from './simple-dialog-title.component';
import { MaterialModule } from '@ms/shared';

@NgModule({
    declarations: [
        SimpleDialogTitleComponent
    ],
    imports: [CommonModule, MaterialModule],
    exports: [
        SimpleDialogTitleComponent
    ]
})
export class SimpleDialogTitleModule {
}
