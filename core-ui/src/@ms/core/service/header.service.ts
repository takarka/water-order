import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { PrincipalService } from '../auth/principal.service';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
    providedIn: 'root'
})
export class HeaderInterceptor implements HttpInterceptor {
    lang = 'en';
    constructor(private service: PrincipalService,
        private _translateService: TranslateService) {}
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<HttpEventType.Response>> {
        console.log('Authenticated:', this.service.isAuthenticated());
        this.lang = this._translateService.currentLang;
        if (this.service.isAuthenticated()) { 
            console.log('Tenant', this.service.userIdentity.tenant);
            request = request.clone({ headers: request.headers
                .set('X-TENANT', this.service.userIdentity.tenant.name) 
                .set('X-LANG', this.lang)
                .set('Content-Type', 'application/vnd.uaa-v1+json')
            });
        }
        console.log('Header:', request);
        return next.handle(request);
    }
}
