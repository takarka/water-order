export class Region {
    constructor(
        public id?: string,
        public name?: string,
    ) {
        this.id = id ? id : null;
        this.name = name ? name : '';
    }
}

export class City {
    constructor(
        public id?: string,
        public name?: string,
    ) {
        this.id = id ? id : null;
        this.name = name ? name : '';
    }
}

export class Address {
    constructor(
        public name?: string,
        public postalCode?: string,
        public city?: City,
        public region?: Region,
        public street?: string,
        public buildingNumber?: string,
        public entranceNumber?: string,
        public floor?: string,
        public entranceCode?: string,
        public flatNumber?: string,
    ) {
        this.name = name ? name : '';
        this.postalCode = postalCode ? postalCode : '';
        this.city = city ? city : new City();
        this.region = region ? region : new Region();
        this.street = street ? street : '';
        this.buildingNumber = buildingNumber ? buildingNumber : '';
        this.entranceNumber = entranceNumber ? entranceNumber : '';
        this.floor = floor ? floor : '';
        this.entranceCode = entranceCode ? entranceCode : '';
        this.flatNumber = flatNumber ? flatNumber : '';
    }
}


