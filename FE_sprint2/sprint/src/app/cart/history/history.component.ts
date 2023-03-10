import { Component, OnInit } from '@angular/core';
import {Oder} from '../../model/interface/oder';
import {CartService} from '../../service/cart.service';
import {TokenService} from '../../service/token.service';
import {OderDetailInfo} from '../../model/interface/oder-detail-info';
import {ImageProducts} from '../../model/interface/image-products';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  oderDetailInfo: OderDetailInfo[] = [];
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

  showDetail(id: number): void {
    this.cartService.getHistoryPayDetail(id).subscribe(data => {
      console.log(data);
      this.oderDetailInfo = data;
    });
  }
}
