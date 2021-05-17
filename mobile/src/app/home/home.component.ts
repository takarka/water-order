import { Component, OnInit } from "@angular/core";
import { NavigationService } from "../navigation.service";
import { ProductService, Product } from "../services/product.service";
import { RouterExtensions } from "nativescript-angular/router";
import { BasketService } from "../services/basket.service";

@Component({
  selector: "ns-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
  moduleId: module.id
})
export class HomeComponent implements OnInit {
  amount: number = 1;
  currentProduct: Product = new Product();
  constructor(
    private navigationService: NavigationService,
    private productService: ProductService,
    private basketService: BasketService,
    private routerExtension: RouterExtensions
  ) {}

  ngOnInit() {
    console.log("HOME");
    setTimeout(() => {
      this.navigationService.changeTab(1);
    });
    this.productService.getAll().subscribe(products => {
      console.log("got products:", products)
      this.currentProduct = products[0];
      console.log("selected product:", this.currentProduct)

    });
  }
  order() {
    this.basketService.resetBasket();
    this.basketService.addProduct(this.currentProduct, this.amount);
    this.routerExtension.navigate(['/basket']);
  }
  increaseAmount() {
    this.amount = this.amount+1;
    console.log(this.amount);
  }
  decreaseAmount() {
    if (this.amount > 1) {
      this.amount--;
    }
    console.log(this.amount);
  }
}
