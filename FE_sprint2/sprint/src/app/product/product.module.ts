import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductPromotionComponent } from './product-promotion/product-promotion.component';
import {CarouselModule} from 'ngx-owl-carousel-o';
import { ProductPromotionSpecialComponent } from './product-promotion-special/product-promotion-special.component';


@NgModule({
  declarations: [ ProductListComponent, ProductDetailComponent, ProductPromotionComponent, ProductPromotionSpecialComponent],
  imports: [
    CommonModule,
    ProductRoutingModule,
    CarouselModule
  ]
})
export class ProductModule { }
