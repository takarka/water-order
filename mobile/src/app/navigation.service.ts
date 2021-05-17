import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable()
export class NavigationService {
  private tabChange = new Subject<number>();

  changeTab(index: number) {
    this.tabChange.next(index);
  }
  get tab() {
    return this.tabChange;
  }
}
