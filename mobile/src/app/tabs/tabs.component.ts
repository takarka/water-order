import { Component, OnInit } from '@angular/core';
import { RouterExtensions } from 'nativescript-angular/router';
import { ActivatedRoute } from '@angular/router';
import { Page } from 'tns-core-modules/ui/page/page';
import { SelectedIndexChangedEventData } from 'tns-core-modules/ui/tab-view/tab-view';
import { NavigationService } from '../navigation.service';

@Component({
  selector: 'ns-tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.css'],
  moduleId: module.id,
})
export class TabsComponent implements OnInit {
  constructor(
    private _page: Page,
    private routerExtension: RouterExtensions,
    private activeRoute: ActivatedRoute,
    private navigationService: NavigationService
  ) {
    this._page.actionBarHidden = true;
  }

  ngOnInit() {
    this.routerExtension.navigate(
      [
        {
          outlets: {
            profileTab: ['profile'],
            homeTab: ['home'],
            historyTab: ['history'],
          },
        },
      ],
      { relativeTo: this.activeRoute }
    );
  }
  onSelectedIndexChanged(args: SelectedIndexChangedEventData) {
    if (args.oldIndex !== -1) {
      const newIndex = args.newIndex;
      this.navigationService.changeTab(newIndex);
    }
  }
}
