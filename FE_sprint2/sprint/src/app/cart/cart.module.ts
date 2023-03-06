import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartRoutingModule } from './cart-routing.module';
import { CartListComponent } from './cart-list/cart-list.component';
import { AddToCartComponent } from './add-to-cart/add-to-cart.component';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [CartListComponent, AddToCartComponent],
    imports: [
        CommonModule,
        CartRoutingModule,
        FormsModule
    ]
})
export class CartModule { }
