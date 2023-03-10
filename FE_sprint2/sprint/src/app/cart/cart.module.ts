import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartRoutingModule } from './cart-routing.module';
import { CartListComponent } from './cart-list/cart-list.component';
import { AddToCartComponent } from './add-to-cart/add-to-cart.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PaymentComponent } from './payment/payment.component';
import { HistoryComponent } from './history/history.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { PaymentSuccessComponent } from './payment-success/payment-success.component';


@NgModule({
  declarations: [CartListComponent, AddToCartComponent, PaymentComponent, HistoryComponent, PaymentSuccessComponent],
    imports: [
        CommonModule,
        CartRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        NgxPaginationModule
    ]
})
export class CartModule { }
