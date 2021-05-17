export class Product {
    constructor(
        public id?: string,
        public name?: string,
        public code?: string,
        public description?: string,
        public imageUrl?: string,
        public price?: number,
        public productCategory?: ProductCategory,
    ) {
        this.id = id ? id : null;
        this.name = name ? name : '';
        this.code = code ? code : '';
        this.description = description ? description : '';
        this.imageUrl = imageUrl ? imageUrl : '';
        this.price = price ? price : 0;
        this.productCategory = productCategory ? productCategory : null;
    }
}

export class ProductCategory {
    constructor(
        public id?: string,
        public name?: string,
        public parent?: ProductCategory,
    ) {
        this.id = id ? id : null;
        this.name = name ? name : '';
        this.parent = parent ? parent : null;
    }
}
