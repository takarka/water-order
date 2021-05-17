import { CanActivate, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private auth: AuthService,
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (!this.auth.isTokenExpired()) {
            return true;
        } else {
            console.log('OnlyLoggedInUsers');
            this.router.navigate(['login'], { queryParams: { returnUrl: route.url || '/' }, queryParamsHandling: "merge" });
            return false;
        }
    }
}
