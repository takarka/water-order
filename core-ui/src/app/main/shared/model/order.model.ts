import { Address } from './address.model';
import { Product } from './product.model';
import { User } from './user.model';

export class OrderSearch {
    constructor(
        public orderNumber?: string,
        public phone?: string,
        public status?: OrderStatus
    ) {
        this.orderNumber = orderNumber ? orderNumber : '';
        this.phone = phone ? phone : '';
        this.status = status ? status : null;
    }
}

export class Order {
    constructor(
        public id?: string,
        public orderNumber?: string,
        public phone?: string,
        public totalQuantity?: number,
        public totalPrice?: number,
        public deliveryAddress?: Address,
        public feedback?: string,
        public comment?: string,
        public estimateDeliveryTime?: Date,
        public details?: OrderDetail[],
        public status?: OrderStatus,
        public manufactureCancel?: boolean,
        public orderTimeRange?: OrderTime,
        public user?: User,
    ) {
        this.id = id ? id : null;
        this.orderNumber = orderNumber ? orderNumber : '';
        this.phone = phone ? phone : '';
        this.totalQuantity = totalQuantity ? totalQuantity : 0;
        this.totalPrice = totalPrice ? totalPrice : 0;
        this.deliveryAddress = deliveryAddress ? deliveryAddress : new Address();
        this.feedback = feedback ? feedback : '';
        this.comment = comment ? comment : '';
        this.estimateDeliveryTime = estimateDeliveryTime ? estimateDeliveryTime : new Date();
        this.details = details ? details : [];
        this.status = status ? status : OrderStatus.CREATED;
        this.manufactureCancel = manufactureCancel ? manufactureCancel : false;
        this.orderTimeRange = orderTimeRange ? orderTimeRange : new OrderTime();
        this.user = user ? user : new User();
        }
}
export class OrderTime {
    id: string;
    name: string;
    start: string;
    end: string;
  }
export class OrderDetail {
    constructor(
        public product?: Product,
        public price?: number,
        public quantity?: number,
        public totalPrice?: number
    ) {
        this.product = product ? product : new Product();
        this.price = price ? price : 0;
        this.quantity = quantity ? quantity : 0;
        this.totalPrice = totalPrice ? totalPrice : 0;
    }
}

export enum OrderStatus {
    CREATED = 'Создан',
    APPROVED = 'Одобрен',
    CANCELED = 'Отменен',
    DELIVERING = 'В доставке',
    ORDERING = 'В заказе',
}
