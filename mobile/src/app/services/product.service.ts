import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { API_BASE_URL } from "../constants/urls";

@Injectable()
export class ProductService {
  private url = API_BASE_URL + "/api/product";
  private headers = new HttpHeaders({ "Content-Type": "application/json" });
  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Product[]>(`${this.url}/`, {
      headers: this.headers
    });
  }
}
export class Product {
  id: string;
  name: string;
  code: string;
  description: string;
  imageUrl: string;
  productCategory: ProductCategory;
  price: number;
}

export class ProductCategory {
  id: string;
  name: string;
  parent: ProductCategory;
}
