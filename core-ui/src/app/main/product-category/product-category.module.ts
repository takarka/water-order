import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { AuthenticantionGuard } from '@ms/core/auth/authentication.guard';
import { CoreDirectivesModule } from '@ms/core/directive/directives';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';
import { TranslateModule } from '@ngx-translate/core';
import { ButtonBarModule } from '../../layout/components/buttonbar/buttonbar.module';
import { ProductCategoryComponent } from './product-category.component';
import { SimpleDialogTitleModule } from '@ms/core/components/dialog/simple-dialog/title/simple-dialog-title.module';

const routes = [
  {
    path: '',
    component: ProductCategoryComponent,
    canActivate: [AuthenticantionGuard],
  },
];

@NgModule({
  declarations: [ProductCategoryComponent],
  imports: [
    RouterModule.forChild(routes),
    TranslateModule,
    FuseSharedModule,
    ButtonBarModule,
    CoreDirectivesModule,
    SimpleDialogTitleModule,
  ],
  exports: [ProductCategoryComponent, RouterModule],
  entryComponents: [],
})
export class ProductCategoryModule {
  constructor(@Optional() @SkipSelf() parentModule: ProductCategoryModule) {
    throwIfAlreadyLoaded(parentModule, 'ProductCategoryModule');
  }
}
