import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private resultSearch: BehaviorSubject<any> = new BehaviorSubject<any>('');
  constructor() { }

  setValue(value: any): void {
    this.resultSearch.next(value);
  }

  getValue(): Observable<any> {
    return this.resultSearch.asObservable();
  }
}
