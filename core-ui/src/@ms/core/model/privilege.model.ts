export class Privilege {
    id?: string = null;
    uuid?: string;
    privilegeName?: string;
    description?: string;
    tag?: string;
    showMenu?: boolean;
    parentId?: string = null;
    serviceId?: string = null;
    orderNo?: number;
    publicPrivilege?: boolean;
    shortcutPrivilege?: boolean;
    active?: boolean;
    code?: string;
    type?: string;
}
