import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanLoad, Route } from '@angular/router';
import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';

import * as fromRoot from '../app.reducer';
import { PrincipalService } from '@ms/core/auth/principal.service';


@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

  constructor(
      private store: Store<fromRoot.State>, 
      private router: Router,
      private principal: PrincipalService, 
    ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (this.principal.isAuthenticated()) {
        return true;
    }
    this.router.navigate(['login']);
    return false;
    // return this.store.select(fromRoot.getIsAuth).pipe(take(1));
  }

  canLoad(route: Route) {
    return true;
    // return this.store.select(fromRoot.getIsAuth).pipe(take(1));
  }
}
