import { FuseNavigation } from '@fuse/types';

export const navigation: FuseNavigation[] = [
  {
    id: 'applications',
    title: 'Applications',
    translate: 'NAV.APPLICATIONS',
    type: 'group',
    children: [
      {
        id: 'order-tiem-range',
        title: 'Time Range',
        translate: 'NAV.ORDER-TIME-RANGE.TITLE',
        type: 'item',
        icon: 'receipt',
        url: '/order-time-range',
      },
      {
        id: 'orders',
        title: 'Orders',
        translate: 'NAV.ORDERS.TITLE',
        type: 'item',
        icon: 'receipt',
        url: '/orders',
      },
      {
        id: 'product-category',
        title: 'Product Category',
        translate: 'NAV.PRODUCT_CATEGORY.TITLE',
        type: 'item',
        icon: 'receipt',
        url: '/product-category',
      },
    ],
  },
  {
    id: 'settings',
    title: 'Settings',
    translate: 'NAV.SETTINGS',
    type: 'group',
    children: [
      {
        id: 'user-management',
        title: 'User Management',
        translate: 'NAV.USER_MANAGEMENT.TITLE',
        type: 'item',
        icon: 'receipt',
        url: '/user'
      }
    ]
  }
];
