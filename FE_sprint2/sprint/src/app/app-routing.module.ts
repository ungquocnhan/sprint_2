import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from './authGuard/auth.guard';
import {UserGuard} from './authGuard/user.guard';

const routes: Routes = [
  {path: 'home', loadChildren: () => import('./home/home.module').then(module => module.HomeModule)},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {
    path: 'security', loadChildren: () => import('./security/security.module').then(module => module.SecurityModule)
  },
  {
    path: 'product', loadChildren: () => import('./product/product.module').then(module => module.ProductModule)
  },
  {
    path: 'cart', loadChildren: () => import('./cart/cart.module').then(module => module.CartModule),
    canActivate: [AuthGuard, UserGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
