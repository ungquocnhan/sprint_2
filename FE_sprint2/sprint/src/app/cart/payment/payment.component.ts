import {Component, OnInit} from '@angular/core';
import {render} from 'creditcardpayments/creditCardPayments';
import {CartService} from '../../service/cart.service';
import {TokenService} from '../../service/token.service';
import {CartDto} from '../../model/interface/cart-dto';
import {SearchService} from '../../service/search.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PayDto} from '../../model/interface/pay-dto';
import {CustomerService} from '../../service/customer.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  cartList: CartDto[] = [];
  idCustomer = 0;
  nameCustomer = '';
  phoneNumber = '';
  address = '';
  sumMoneyAll = 0;
  formPay: FormGroup = new FormGroup({});
  payDto: PayDto = {};

  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private searchService: SearchService,
              private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.formPay = new FormGroup({
      name: new FormControl('', Validators.required),
      phoneNumber: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      note: new FormControl('')
    });
    this.getCartList();
    this.getCustomerById();
  }

  getCustomerById(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.customerService.getCustomer(this.idCustomer).subscribe(data => {
      console.log(data);
      this.formPay.patchValue(data[0]);
    });
  }

  getCartList(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.getCartByIdCustomer(this.idCustomer).subscribe(data => {
      this.cartList = data[0];
      this.sumMoneyAll = data[1];
      this.searchService.setCount(this.cartList.length);
      render({
        id: '#myPaypalButton',
        currency: 'VND',
        value: (this.sumMoneyAll / 100000).toFixed(2),
        onApprove: (details) => {
          // this.payDto = this.formPay.value;
          // // @ts-ignore
          // this.payDto.idCustomer = +this.tokenService.getIdCustomer();
          // this.payDto.total = this.sumMoneyAll;
          // console.log(this.payDto);
          // this.cartService.paymentCart(this.payDto).subscribe(() => {
          //   this.ngOnInit();
          // });
        }
      });
    });
  }

  pay(): void {
    this.payDto = this.formPay.value;
    // @ts-ignore
    this.payDto.idCustomer = +this.tokenService.getIdCustomer();
    this.payDto.total = this.sumMoneyAll;
    console.log(this.payDto);
    this.cartService.paymentCart(this.payDto).subscribe(() => {
      this.ngOnInit();
    });
  }
}
