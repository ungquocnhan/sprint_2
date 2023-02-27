import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/interface/product';
import {ImageProducts} from '../model/interface/image-products';
import {ProductInfo} from '../model/interface/product-info';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  URL_PRODUCT = 'http://localhost:8080/api/products';
  URL_IMAGEPRODUCT = 'http://localhost:8080/images';

  constructor(private httpClient: HttpClient) {
  }

  getAll(request: any): Observable<any> {
    const params = request;
    return this.httpClient.get<any>(this.URL_PRODUCT, {params});
  }

  findById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(this.URL_PRODUCT + '/' + id);
  }

  getAllImage(): Observable<ProductInfo[]> {
    return this.httpClient.get<ProductInfo[]>(this.URL_IMAGEPRODUCT);
  }
}
