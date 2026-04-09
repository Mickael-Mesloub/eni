import { Routes } from '@angular/router';

export const SHOP_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./products/products').then((c) => c.Products),
  },
  {
    path: ':id',
    loadComponent: () => import('./product/product').then((c) => c.Product),
  },
];
