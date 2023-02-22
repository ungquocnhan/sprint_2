import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../interface/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  URL_PRODUCT = 'http://localhost:8080/api/products';

  constructor(private httpClient: HttpClient) {
  }

  getAll(request: any): Observable<any> {
    const params = request;
    return this.httpClient.get<any>(this.URL_PRODUCT, {params});
  }

  findById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(this.URL_PRODUCT + '/' + id);
  }
}
