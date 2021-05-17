import { Component, OnInit } from "@angular/core";

import { Item } from "./item";
import { ItemService } from "./item.service";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

@Component({
    selector: "ns-items",
    moduleId: module.id,
    templateUrl: "./items.component.html",
})
export class ItemsComponent implements OnInit {
    items: Item[];
    form: FormGroup;                    // {1}

    // This pattern makes use of Angular’s dependency injection implementation to inject an instance of the ItemService service into this class.
    // Angular knows about this service because it is included in your app’s main NgModule, defined in app.module.ts.
    constructor(private itemService: ItemService,     private fb: FormBuilder, ) { }

    ngOnInit(): void {
        this.form = this.fb.group({     // {5}
            name: ['', Validators.required],
          });
        this.itemService.getItems().subscribe(results => this.items = results);
    }
    onSubmit() {
        if (this.form.valid) {
          this.itemService.create(this.form.value).subscribe(
            () => {
                this.itemService.getItems().subscribe(results => this.items = results);

            },
            (error) => {
              console.log('error');
              console.log(error);
            }
          );
        }
      }
}