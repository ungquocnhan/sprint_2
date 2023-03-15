import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductPromotionComponent } from './product-promotion/product-promotion.component';
import {CarouselModule} from 'ngx-owl-carousel-o';
import { ProductPromotionSpecialComponent } from './product-promotion-special/product-promotion-special.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [ ProductDetailComponent, ProductPromotionComponent, ProductPromotionSpecialComponent],
    imports: [
        CommonModule,
        ProductRoutingModule,
        CarouselModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class ProductModule { }
