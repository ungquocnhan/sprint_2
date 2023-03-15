import { Injectable } from '@angular/core';
const TOKEN_KEY = 'Token_key';
const NAME_KEY = 'Name_key';
const ROLE_KEY = 'Role_key';
const AVATAR_KEY = 'Avatar_key';
const ID_KEY = 'Id_key';
const EMAIL_KEY = 'Email_key';
const CUSTOMER_ID_KEY = 'Customer_Id_key';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  roles: any[] = [];

  constructor() {
  }
  public setIdCustomer(idCustomer: string): void {
    sessionStorage.removeItem(CUSTOMER_ID_KEY);
    sessionStorage.setItem(CUSTOMER_ID_KEY, idCustomer);
  }

  public getIdCustomer(): string | null {
    return sessionStorage.getItem(CUSTOMER_ID_KEY);
  }
  public setEmail(email: string): void {
    sessionStorage.removeItem(EMAIL_KEY);
    sessionStorage.setItem(EMAIL_KEY, email);
  }

  public getEmail(): string | null {
    return sessionStorage.getItem(EMAIL_KEY);
  }

  public setId(id: string): void {
    sessionStorage.removeItem(ID_KEY);
    sessionStorage.setItem(ID_KEY, id);
  }

  public getId(): string | null {
    return sessionStorage.getItem(ID_KEY);
  }

  public setAvatar(avatar: string): void {
    sessionStorage.removeItem(AVATAR_KEY);
    sessionStorage.setItem(AVATAR_KEY, avatar);
  }

  public getAvatar(): string | null {
    return sessionStorage.getItem(AVATAR_KEY);
  }

  public setToken(token: string): void {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public setName(name: string): void {
    sessionStorage.removeItem(NAME_KEY);
    sessionStorage.setItem(NAME_KEY, name);
  }

  public getName(): string | null {
    return sessionStorage.getItem(NAME_KEY);
  }


  public setRole(roles: string[]): void {
    sessionStorage.removeItem(ROLE_KEY);
    sessionStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  }


  public getRole(): string[] {
    this.roles = [];
    if (this.getToken()) {
      // @ts-ignore
      JSON.parse(sessionStorage.getItem(ROLE_KEY)).forEach(role => {
        // @ts-ignore
        this.roles.push(role.authority);
      });
    }
    return this.roles;
  }

  public rememberMe(roles: string[], name: string, token: string, idCustomer: string, email: string, avatar: string, id: string): void {
    this.setRole(roles);
    this.setName(name);
    this.setToken(token);
    this.setIdCustomer(idCustomer);
    this.setEmail(email);
    this.setAvatar(avatar);
    this.setId(id);
  }
}
