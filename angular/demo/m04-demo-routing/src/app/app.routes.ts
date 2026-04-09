import { Routes } from '@angular/router';

export const routes: Routes = [
    {path: '', loadChildren: () => import('./pages/main/main.routes').then(c => c.MAIN_ROUTES)},
    {path: 'products', loadChildren: () => import('./pages/shop/shop.routes').then(c => c.SHOP_ROUTES)},
    {path: 'admin', loadChildren: () => import('./pages/admin/admin.routes').then(c => c.ADMIN_ROUTES)},
    {path: '**', redirectTo: '', pathMatch: 'full'}
];
