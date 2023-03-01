import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/interface/product';
import {ProductInfo} from '../model/interface/product-info';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  URL_PRODUCT = 'http://localhost:8080/api/public/products';
  URL_IMAGEPRODUCT = 'http://localhost:8080/api/public/images';

  constructor(private httpClient: HttpClient) {
  }

  getAll(name: any, request: any): Observable<any> {
    const params = request;
    // tslint:disable-next-line:no-shadowed-variable
    const url = this.URL_PRODUCT + '?name=' + name;
    console.log(url);
    return this.httpClient.get<any>(url, {params});
    // return this.httpClient.get<any>(this.URL_PRODUCT + '?name=' + name, {params});
  }

  findById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(this.URL_PRODUCT + '/' + id);
  }

  getAllImage(): Observable<ProductInfo[]> {
    return this.httpClient.get<ProductInfo[]>(this.URL_IMAGEPRODUCT);
  }
}
