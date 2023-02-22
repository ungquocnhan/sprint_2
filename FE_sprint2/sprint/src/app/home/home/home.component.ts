import { Component, OnInit } from '@angular/core';
import {ProductInfoJson} from '../../interface/product-info-json';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  productList!: ProductInfoJson;
  request = {page: 0, size: 8};
  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getAll(this.request).subscribe(data => {
      console.log(data);
      this.productList = data;
    });
  }



}
