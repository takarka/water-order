import { Component, OnInit, NgZone } from "@angular/core";
import { Router, NavigationExtras } from "@angular/router";
import { handleOpenURL, AppURL } from "nativescript-urlhandler";
import { AuthService } from "./auth/auth.service";
import { init } from 'nativescript-advanced-webview';

@Component({
  selector: "ns-app",
  moduleId: module.id,
  templateUrl: "./app.component.html"
})
export class AppComponent implements OnInit {
  constructor(
    private ngZone: NgZone,
    private router: Router,
    private authService: AuthService,
  ) {}

  ngOnInit() {
    init();

    handleOpenURL((appURL: AppURL) => {
      console.log("Got the following appURL: ", appURL);
      console.log("Got the following path: ", appURL.path);
      let route = appURL.path.substr((appURL.path + "").indexOf("://") + 1);
      const params = Object.create(null);
      appURL.params.forEach((param, key) => {
        if (key === "token") {
          this.authService.setToken(param);
          console.log(this.authService.getToken());
        }
        params[key] = param;
      });
      console.log("Going to following route", route);
      const navigationExtras: NavigationExtras = {
        queryParams: params
      };
      this.ngZone.run(() => this.router.navigate([route], navigationExtras));
    });

  }


}
