import {Component, OnInit} from '@angular/core';
import {CartService} from '../../service/cart.service';
import {CartDto} from '../../model/interface/cart-dto';
import {TokenService} from '../../service/token.service';
import {CartDetail} from '../../model/interface/cart-detail';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {
  cartList: CartDto[] = [];
  idCustomer = 0;
  nameCustomer = '';
  phoneNumber = '';
  address = '';
  sumMoneyAll = 0;
  totalProduct: number | undefined;
  cartDetail: CartDetail = {};
  idDelete = 0;
  temp: CartDto = {};

  constructor(private cartService: CartService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.getCartByIdCustomer(this.idCustomer).subscribe(data => {

      this.cartList = data;
      this.nameCustomer = data[0].nameCustomer;
      this.phoneNumber = data[0].phoneNumber;
      this.address = data[0].address;
      this.sumMoneyAll = this.getSumMoneyAll();
    });
  }

  decrease(idCartDetail: number): void {
    for (const element of this.cartList) {
      if (element.idCartDetail === idCartDetail) {
        // @ts-ignore
        if (element.amount <= 1) {
          element.amount = 1;
        } else {
          // @ts-ignore
          element.amount -= 1;
        }
        this.cartDetail.quantity = element.amount;
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
        if (element.amount >= 10) {
          element.amount = 10;
        } else {
          // @ts-ignore
          element.amount += 1;
        }
        this.cartDetail.quantity = element.amount;
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

  getSumMoneyAll(): number {
    let sumMoneyAll = 0;
    for (const element of this.cartList) {
      // @ts-ignore
      sumMoneyAll += element.price * element.amount;
    }
    return sumMoneyAll;
  }

  change(quantity: number, idCartDetail: number): void {
    if (isNaN(quantity)) {
    }
    this.cartDetail.quantity = quantity;
    this.cartDetail.idCartDetail = idCartDetail;
    this.updateAmount(quantity, idCartDetail);
  }

  deleteIdCartDetail(): void {
      this.cartService.delete(Number(this.temp.idCartDetail)).subscribe(() => {
        this.ngOnInit();
      });
  }
}
