import { ITenant, Tenant } from './tenant.model';

export interface IUser {
    id?: any;
    username?: string;
    firstName?: string;
    lastName?: string;
    email?: string;
    activated?: boolean;
    langKey?: string;
    roles?: any[];
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    password?: string;
    tenantId?: number;
    tenant?: ITenant;
    imageUrl?: string;
    image?: string;
}

export class User implements IUser {
    constructor(
        public id?: any,
        public username?: string,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public activated?: boolean,
        public langKey?: string,
        public roles?: any[],
        public createdBy?: string,
        public createdDate?: Date,
        public lastModifiedBy?: string,
        public lastModifiedDate?: Date,
        public password?: string,
        public locked?: boolean,
        public tenant?: Tenant,
        public imageUrl?: string,
        public image?: string
    ) {
        this.id = id ? id : null;
        this.username = username ? username : null;
        this.firstName = firstName ? firstName : null;
        this.lastName = lastName ? lastName : null;
        this.email = email ? email : null;
        this.activated = activated ? activated : false;
        this.langKey = langKey ? langKey : null;
        this.roles = roles ? roles : null;
        this.createdBy = createdBy ? createdBy : null;
        this.createdDate = createdDate ? createdDate : null;
        this.lastModifiedBy = lastModifiedBy ? lastModifiedBy : null;
        this.lastModifiedDate = lastModifiedDate ? lastModifiedDate : null;
        this.password = password ? password : null;
        this.locked = locked ? locked : null;
        this.tenant = tenant ? tenant : new Tenant();
        this.imageUrl = imageUrl ? imageUrl : null;
        this.image = image ? image : null;
    }
}
