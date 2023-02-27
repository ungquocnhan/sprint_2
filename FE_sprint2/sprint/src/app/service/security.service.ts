import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SignInForm} from '../model/account/sign-in-form';
import {Observable} from 'rxjs';
import {JwtResponse} from '../model/account/jwt-response';
import {Customer} from '../model/interface/customer';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private API_SIGNIN = environment.API_LOCAL + '/signin';
  private API_SIGNUP = environment.API_LOCAL + '/signup';

  constructor(private httpClient: HttpClient) {
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    'Access-Control-Allow-Origin': 'http://localhost:4200',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
  };

  signIn(signInForm: SignInForm): Observable<any> {
    return this.httpClient.post<JwtResponse>(this.API_SIGNIN, signInForm, this.httpOptions);
  }

  register(customer: Customer): Observable<Customer> {
    return this.httpClient.post<Customer>(this.API_SIGNUP, customer, this.httpOptions);
  }
}
