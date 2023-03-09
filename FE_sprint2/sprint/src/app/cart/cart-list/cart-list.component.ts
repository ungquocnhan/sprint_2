import {Component, OnInit} from '@angular/core';
import {CartService} from '../../service/cart.service';
import {CartDto} from '../../model/interface/cart-dto';
import {TokenService} from '../../service/token.service';
import {CartDetail} from '../../model/interface/cart-detail';
import {SearchService} from '../../service/search.service';
import {ToastrService} from 'ngx-toastr';
import {render} from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {
  cartList: CartDto[] = [];
  idCustomer = 0;
  sumMoneyAll = 0;
  totalProduct: number | undefined;
  cartDetail: CartDetail = {};
  temp: CartDto = {};

  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private searchService: SearchService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getCartList();
  }

  getCartList(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.getCartByIdCustomer(this.idCustomer).subscribe(data => {
      console.log(data);
      this.cartList = data[0];
      this.sumMoneyAll = data[1];
      this.searchService.setCount(this.cartList.length);
    });
  }

  decrease(idCartDetail: number): void {
    for (const element of this.cartList) {
      if (element.idCartDetail === idCartDetail) {
        // @ts-ignore
        if (element.quantity <= 1) {
          element.quantity = 1;
        } else {
          // @ts-ignore
          element.quantity -= 1;
        }
        this.cartDetail.quantity = element.quantity;
        this.cartDetail.idCartDetail = idCartDetail;
        this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
        });
      }
    }
    this.ngOnInit();
  }

  increase(idCartDetail: number): void {
    for (const element of this.cartList) {
      if (element.idCartDetail === idCartDetail) {
        // @ts-ignore
        if (element.quantity >= 10) {
          element.quantity = 10;
        } else {
          // @ts-ignore
          element.quantity += 1;
        }
        this.cartDetail.quantity = element.quantity;
        this.cartDetail.idCartDetail = idCartDetail;
        this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
        });
      }
    }
    this.ngOnInit();
  }

  private updateAmount(quantity: number | undefined, idCartDetail: number): void {
    this.cartDetail.quantity = quantity;
    this.cartDetail.idCartDetail = idCartDetail;
    this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
      this.ngOnInit();
    });
  }

  // getSumMoneyAll(): number {
  //   let sumMoneyAll = 0;
  //   // this.items.filter(item => item.selected).reduce((sum, item) => sum + item.price, 0);
  //   for (const element of this.cartList) {
  //     // @ts-ignore
  //     sumMoneyAll += element.price * element.quantity;
  //   }
  //   return sumMoneyAll;
  // }

  change(quantity: number, idCartDetail: number): void {
    if (isNaN(quantity)) {
    }
    this.cartDetail.quantity = quantity;
    this.cartDetail.idCartDetail = idCartDetail;
    this.updateAmount(quantity, idCartDetail);
  }

  deleteIdCartDetail(): void {
    this.cartService.delete(Number(this.temp.idCartDetail)).subscribe(() => {
      this.getCartList();
    });
  }
}
