import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {ProductPromotionComponent} from './product-promotion/product-promotion.component';
import {ProductPromotionSpecialComponent} from './product-promotion-special/product-promotion-special.component';
import {UserGuard} from '../authGuard/user.guard';

const routes: Routes = [
  {
    path: 'detail/:id', component: ProductDetailComponent
  },
  {
    path: 'promotion', component: ProductPromotionComponent
  },
  {
    path: 'special', component: ProductPromotionSpecialComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule {
}
