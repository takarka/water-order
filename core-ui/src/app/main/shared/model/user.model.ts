export class UserSearch {
  constructor(public name?: string) {
    this.name = name ? name : null;
  }
}

export class User {
  constructor(
    public id?: any,
    public name?: string,
    // public firstName?: string,
    // public lastName?: string,
    public email?: string,
    public emailVerified?: boolean,
    public active?: boolean,
    public phone?: string,
    public roles?: any[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public password?: string,
    // public locked?: boolean,
    public imageUrl?: string,
    // public image?: string
    public provider?: AuthProvider
  ) {
    this.id = id ? id : null;
    this.name = name ? name : null;
    // this.firstName = firstName ? firstName : null;
    // this.lastName = lastName ? lastName : null;
    this.email = email ? email : null;
    this.emailVerified = emailVerified ? emailVerified : false;
    this.active = active ? active : false;
    this.phone = phone ? phone : null;
    this.roles = roles ? roles : null;
    this.createdBy = createdBy ? createdBy : null;
    this.createdDate = createdDate ? createdDate : null;
    this.lastModifiedBy = lastModifiedBy ? lastModifiedBy : null;
    this.lastModifiedDate = lastModifiedDate ? lastModifiedDate : null;
    this.password = password ? password : null;
    // this.locked = locked ? locked : null;
    this.imageUrl = imageUrl ? imageUrl : null;
    // this.image = image ? image : null;
    this.provider = provider ? provider : AuthProvider.local;
  }
}

export enum AuthProvider {
  local,
  facebook,
  google,
  github,
}

export enum RoleEnum {
  ADMIN = 'ADMIN',
  SUPPLIER = 'SUPPLIER',
  CUSTOMER = 'CUSTOMER',
}
