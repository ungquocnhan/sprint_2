import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {CartDetail} from '../model/interface/cart-detail';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  URL_CART = 'http://localhost:8080/api/user/carts';

  constructor(private httpClient: HttpClient) {
  }

  addToCart(cartDetail: CartDetail): Observable<any> {
    return this.httpClient.post(this.URL_CART, cartDetail);
  }

  getCartByIdCustomer(id: number): Observable<any> {
    return this.httpClient.get(this.URL_CART + '/' + id);
  }

  updateAmountByCart(cartDetail: CartDetail): Observable<any> {
    return this.httpClient.patch(this.URL_CART, cartDetail);
  }

  delete(idCartDetail: number): Observable<any> {
    return this.httpClient.delete(this.URL_CART + '/' + idCartDetail);
  }
}
