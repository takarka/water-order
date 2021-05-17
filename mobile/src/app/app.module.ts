import { NgModule, NO_ERRORS_SCHEMA, NgModuleFactoryLoader, ErrorHandler } from '@angular/core';
import { NativeScriptModule } from 'nativescript-angular/nativescript.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './auth/login/login.component';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './auth/auth.interceptor';

import { NativeScriptFormsModule } from 'nativescript-angular/forms';
import { NativeScriptHttpClientModule } from 'nativescript-angular/http-client';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NavigationService } from './navigation.service';
import { ProductService } from './services/product.service';
import { OrderService } from './services/order.service';
import { NSModuleFactoryLoader } from 'nativescript-angular/router';
import { BasketComponent } from './basket/basket.component';
import { BasketService } from './services/basket.service';
import { NativeScriptUIDataFormModule } from 'nativescript-ui-dataform/angular';

import { enable as traceEnable } from 'tns-core-modules/trace';
import { RegistrationComponent } from './auth/login/registration/registration.component';
import { ProfileService } from './services/profile.service';
import { EditProfileComponent } from './profile/edit-profile/edit-profile.component';
import { OrderDetailComponent } from './history/order-detail/order-detail.component';
import { EditAddressComponent } from './profile/edit-address/edit-address.component';
traceEnable();

export class MyErrorHandler implements ErrorHandler {
  handleError(error) {
    if (error.stack) {
      console.log('### ErrorHandler Error: ' + error.toString());
      console.log('### ErrorHandler Stack: ' + error.stack);
    } else {
      console.log(error);
    }
  }
}
@NgModule({
  bootstrap: [AppComponent],
  imports: [
    NativeScriptModule,
    AppRoutingModule,
    NativeScriptHttpClientModule,
    NativeScriptCommonModule,
    NativeScriptFormsModule,
    ReactiveFormsModule,
    NativeScriptUIDataFormModule,
  ],
  declarations: [AppComponent, LoginComponent, BasketComponent, RegistrationComponent, EditProfileComponent, EditAddressComponent,
    OrderDetailComponent],
  providers: [
    AuthService,
    AuthGuard,
    OrderService,
    ProductService,
    BasketService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    { provide: NgModuleFactoryLoader, useClass: NSModuleFactoryLoader },
    { provide: ErrorHandler, useClass: MyErrorHandler },
    ProfileService,
    NavigationService,
  ],
  schemas: [NO_ERRORS_SCHEMA],
})
/*
Pass your application module to the bootstrapModule function located in main.ts to start your app
*/
export class AppModule {}
