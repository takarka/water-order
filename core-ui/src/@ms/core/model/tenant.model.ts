export class TenantProperty {
    constructor(public key: string, public value: string, public active: boolean) {}
}

export interface ITenant {
    id?: string;
    name?: string;
    active?: boolean;
    description?: string;
    expireDate?: Date;
    maxUser?: number;
    properties?: TenantProperty[];
}
export class Tenant implements ITenant {
    constructor(
        public id?: string,
        public name?: string,
        public active?: boolean,
        public description?: string,
        public expireDate?: Date,
        public maxUser?: number,
        public properties?: TenantProperty[]
    ) {
        this.id = id ? id : null;
        this.name = name ? name : null;
        this.active = active ? active : true;
        this.description = description ? description : null;
        this.properties = properties ? properties : [];
        this.expireDate = expireDate ? expireDate : null;
        this.maxUser = maxUser ? maxUser : 0;
    }
}
