import { Component, OnInit } from '@angular/core';
import {Oder} from '../../model/interface/oder';
import {CartService} from '../../service/cart.service';
import {TokenService} from '../../service/token.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  historyPayList: Oder[] = [];
  idCustomer = 0;
  page = 1;
  itemPerPage = 5;
  constructor(private cartService: CartService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.getAllHistoryPay();
  }

  private getAllHistoryPay(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.historyPay(this.idCustomer).subscribe(data => {
      console.log(data);
      this.historyPayList = data;
    });
  }

  onTableDataChange(event: any): void {
    this.page = event;
    this.getAllHistoryPay();
  }
}
