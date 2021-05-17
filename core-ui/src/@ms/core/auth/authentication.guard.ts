import { CanActivate, Router, RouterStateSnapshot, ActivatedRouteSnapshot, CanLoad, Route } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable()
export class AuthenticantionGuard implements CanActivate, CanLoad {
    constructor(
        private router: Router,
        private auth: AuthService,
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (!this.auth.isTokenExpired()) {
            return true;
        } else {
            console.log('OnlyLoggedInUsers');
            this.router.navigate(['login'], { queryParams: { returnUrl: route.url || '/' }, queryParamsHandling: 'merge' });
            return false;
        }
    }
    canLoad(route: Route) {
        if (!this.auth.isTokenExpired()) {
            return true;
        } else {
            console.log('OnlyLoggedInUsers');
            this.router.navigate(['login'], { queryParams: { returnUrl: route.path || '/' }, queryParamsHandling: 'merge' });
            return false;
        }
      }
}
