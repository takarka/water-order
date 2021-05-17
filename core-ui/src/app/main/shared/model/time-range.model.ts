import { Time } from '@angular/common';

export class OrderTimeRange {
    constructor(
        public id?: string,
        public start?: string,
        public end?: string,
        public order?: number
    ) {
        this.id = id ? id : null;
        this.start = start ? start : null;
        this.end = end ? end : null;
        this.order = order ? order : 0;
    }
}
