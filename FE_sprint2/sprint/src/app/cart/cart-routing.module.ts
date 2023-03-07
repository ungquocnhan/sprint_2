import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CartListComponent} from './cart-list/cart-list.component';
import {AddToCartComponent} from './add-to-cart/add-to-cart.component';
import {PaymentComponent} from './payment/payment.component';

const routes: Routes = [
  {
    path: '', component: CartListComponent
  },
  {
    path: 'add', component: AddToCartComponent
  },
  {
    path: 'payment', component: PaymentComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
