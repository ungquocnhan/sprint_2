import { Component, OnInit } from '@angular/core';
import {Oder} from '../../model/interface/oder';
import {CartService} from '../../service/cart.service';
import {TokenService} from '../../service/token.service';
import {OderDetailInfo} from '../../model/interface/oder-detail-info';
import {ImageProducts} from '../../model/interface/image-products';
import {Title} from '@angular/platform-browser';

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
  imagePay: string | undefined;
  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private title: Title) {
    this.title.setTitle('Lịch sử đặt hàng');
  }

  ngOnInit(): void {
    this.getAllHistoryPay();
  }

  private getAllHistoryPay(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.historyPay(this.idCustomer).subscribe(data => {
      this.historyPayList = data;
      this.imagePay = data.product.imageProducts[0].url;
    });
  }

  onTableDataChange(event: any): void {
    this.page = event;
    this.getAllHistoryPay();
  }

  showDetail(id: number): void {
    this.cartService.getHistoryPayDetail(id).subscribe(data => {
      this.oderDetailInfo = data;
    });
  }
}
