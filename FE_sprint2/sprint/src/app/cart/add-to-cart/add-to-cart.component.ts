import { Component, OnInit } from '@angular/core';
import {CartService} from '../../service/cart.service';

@Component({
  selector: 'app-add-to-cart',
  templateUrl: './add-to-cart.component.html',
  styleUrls: ['./add-to-cart.component.css']
})
export class AddToCartComponent implements OnInit {

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
  }

}
