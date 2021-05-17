import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { PrincipalService } from '../auth/principal.service';
import { LoginService } from '../components/login/login.service';


export class AuthExpiredInterceptor implements HttpInterceptor {
    constructor(
        private principal: PrincipalService, 
        private router: Router, 
        private loginService: LoginService
    ) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            tap(
                (event: HttpEvent<any>) => {},
                (err: any) => {
                    if (err instanceof HttpErrorResponse) {
                        if (err.status === 401) {
                            if (this.principal.isAuthenticated()) {
                                this.principal.authenticate(null);
                                this.router.navigate(['/main']);
                            } else {
                                this.loginService.logout();
                                this.router.navigate(['/login']);
                            }
                        }
                    }
                }
            )
        );
    }
}
