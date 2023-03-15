import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CartListComponent} from './cart-list/cart-list.component';
import {AddToCartComponent} from './add-to-cart/add-to-cart.component';
import {PaymentComponent} from './payment/payment.component';
import {HistoryComponent} from './history/history.component';
import {PaymentSuccessComponent} from './payment-success/payment-success.component';
import {AuthGuard} from '../authGuard/auth.guard';
import {UserGuard} from '../authGuard/user.guard';

const routes: Routes = [
  {
    path: '', component: CartListComponent, canActivate: [AuthGuard, UserGuard]
  },
  {
    path: 'add', component: AddToCartComponent, canActivate: [AuthGuard, UserGuard]
  },
  {
    path: 'payment', component: PaymentComponent, canActivate: [AuthGuard, UserGuard]
  },
  {
    path: 'history', component: HistoryComponent, canActivate: [AuthGuard, UserGuard]
  },
  {
    path: 'payment-success', component: PaymentSuccessComponent, canActivate: [AuthGuard, UserGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
