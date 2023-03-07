import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private resultSearch: BehaviorSubject<any> = new BehaviorSubject<any>('');
  itemCount: BehaviorSubject<any> = new BehaviorSubject<number>(0);
  constructor() { }

  setValue(value: string): void {
    this.resultSearch.next(value);
  }

  getValue(): Observable<any> {
    return this.resultSearch.asObservable();
  }

  getCount(): Observable<any> {
    return this.itemCount.asObservable();
  }

  setCount(count: number): void {
    this.itemCount.next(count);
  }
}
