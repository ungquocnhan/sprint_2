import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: 'home', loadChildren: () => import('./home/home.module').then(module => module.HomeModule)},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {
    path: 'security', loadChildren: () => import('./security/security.module').then(module => module.SecurityModule)
  },
  {
    path: 'product', loadChildren: () => import('./product/product.module').then(module => module.ProductModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
