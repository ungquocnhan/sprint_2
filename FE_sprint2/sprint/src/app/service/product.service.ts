import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/interface/product';
import {ProductInfo} from '../model/interface/product-info';
import {Manufacture} from '../model/interface/manufacture';
import {ImageProducts} from '../model/interface/image-products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  URL_PRODUCT = 'http://localhost:8080/api/public/products';
  URL_IMAGEPRODUCT = 'http://localhost:8080/api/public/images';

  URL_MANUFACTURE = 'http://localhost:8080/api/public/manufactures';

  constructor(private httpClient: HttpClient) {
  }

  getAll(name: string, request: any): Observable<any> {
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

  getImageProductById(id: number): Observable<ImageProducts[]> {
    return this.httpClient.get<ImageProducts[]>(this.URL_IMAGEPRODUCT + '/' + id);
  }

  getAllManufacture(): Observable<Manufacture[]> {
    return this.httpClient.get<Manufacture[]>(this.URL_MANUFACTURE);
  }

  getProductPromotion(request: any): Observable<any> {
    const params = request;
    return this.httpClient.get(this.URL_PRODUCT + '/promotion', {params});
  }

  getProductPromotionSpecial(request: any): Observable<any> {
    const params = request;
    return this.httpClient.get(this.URL_PRODUCT + '/special', {params});
  }
}
