import {Component, OnInit} from '@angular/core';
import {CartService} from '../../service/cart.service';
import {CartDto} from '../../model/interface/cart-dto';
import {TokenService} from '../../service/token.service';
import {CartDetail} from '../../model/interface/cart-detail';
import {SearchService} from '../../service/search.service';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {
  cartList: CartDto[] = [];
  idCustomer = 0;
  sumMoneyAll = 0;
  cartDetail: CartDetail = {};
  temp: CartDto = {};
  message = '';
  amountExist = 0;

  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private searchService: SearchService,
              private toastrService: ToastrService,
              private title: Title) {
    this.title.setTitle('Giỏ hàng');
  }

  ngOnInit(): void {
    this.getCartList();
  }

  getCartList(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.getCartByIdCustomer(this.idCustomer).subscribe(data => {
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
        this.getCartList();
      }
    }
  }

  increase(idCartDetail: number): void {
    for (const element of this.cartList) {
      if (element.idCartDetail === idCartDetail) {
        // @ts-ignore
        if (element.quantity >= element.amountExist) {
          element.quantity = element.amountExist;
          this.toastrService.warning('Số lượng bạn chọn đã bằng số lượng sản phẩm còn trong kho.', 'Thông báo');
        } else {
          // @ts-ignore
          element.quantity += 1;
        }
        this.cartDetail.quantity = element.quantity;
        this.cartDetail.idCartDetail = idCartDetail;
        this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
        });
        this.getCartList();
      }
    }
  }

  private updateAmount(quantity: number | undefined, idCartDetail: number): void {
    this.cartDetail.quantity = quantity;
    this.cartDetail.idCartDetail = idCartDetail;
    this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
      this.getCartList();
    });
  }

  change(quantity: number, idCartDetail: number): void {
    this.getAmountExistProduct(idCartDetail);
    if (isNaN(quantity)) {
      this.toastrService.warning('Bạn không được nhập chữ vào đây.', 'Thông báo');
      this.getCartList();
    } else if (quantity >= this.amountExist) {
      this.toastrService.warning('Số lượng bạn nhập lớn hơn số lượng sản phẩm còn trong kho.', 'Thông báo');
      this.getCartList();
    } else if (quantity < 0) {
      this.toastrService.warning('Số lượng bạn nhập phải lớn hơn 0.', 'Thông báo');
      this.getCartList();
    } else {
      this.cartDetail.quantity = quantity;
      this.cartDetail.idCartDetail = idCartDetail;
      this.updateAmount(quantity, idCartDetail);
    }
  }

  private getAmountExistProduct(idCartDetail: number): void {
    this.cartService.getAmountExist(idCartDetail).subscribe(data => {
      console.log(data);
      this.amountExist = data.amountExist;
    });
  }

  deleteIdCartDetail(): void {
    this.cartService.delete(Number(this.temp.idCartDetail)).subscribe(() => {
      this.getCartList();
    });
  }
}
