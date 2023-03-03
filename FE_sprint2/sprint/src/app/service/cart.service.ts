import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
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
}
