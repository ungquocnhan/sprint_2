import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  URL_CUSTOMER = 'http://localhost:8080/api/user/customers';

  constructor(private httpClient: HttpClient) {
  }

  getCustomer(idCustomer: number): Observable<any> {
    return this.httpClient.get(this.URL_CUSTOMER + '/' + idCustomer);
  }
}
