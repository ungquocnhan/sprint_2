import {Component, OnInit} from '@angular/core';
import {render} from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  constructor() {
    // render({
    //   id: '#myPaypalButton',
    //   currency: 'USD',
    //   value: 100,
    //   // value: this.sumMoneyAll / 100000,
    //   onApprove: (details) => {
    //     // this.toastrService.success('Thanh toán thành công');
    //   }
    // });
  }

  ngOnInit(): void {
  }

}
