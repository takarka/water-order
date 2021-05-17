export class ProductCategorySearch {
  constructor(public name?: string) {
    this.name = name ? name : '';
  }
}

export class ProductCategory {
  constructor(public id?: string, public parent?: ProductCategory, public name?: string) {
    this.id = id ? id : null;
    this.parent = parent ? parent : { id: null };
    this.name = name ? name : '';
  }
}
