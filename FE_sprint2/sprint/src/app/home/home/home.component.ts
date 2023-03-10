import {Component, OnInit} from '@angular/core';
import {ProductInfoJson} from '../../model/interface/product-info-json';
import {ProductService} from '../../service/product.service';
import {ImageProducts} from '../../model/interface/image-products';
import {OwlOptions} from 'ngx-owl-carousel-o';
import {ProductInfo} from '../../model/interface/product-info';
import {SearchService} from '../../service/search.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: false,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    navSpeed: 500,
    autoplay: true,
    navText: ['<-', '->'],
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      },
      940: {
        items: 4
      }
    },
    nav: true
  };

  productList!: ProductInfoJson;
  request = {page: 0, size: 8};
  imageList: ProductInfo[] = [];
  valueSearch = '';
  total = 0;

  constructor(private productService: ProductService,
              private searchService: SearchService) {
  }

  ngOnInit(): void {

    this.productService.getAllImage().subscribe(data => {
      console.log(data);
      this.imageList = data;
    });
    this.searchService.getValue().subscribe(value => {
      this.valueSearch = value;
      this.getAllProduct(this.request);
    });
  }


  getAllProduct(request: { page: number, size: number } | undefined): void {
    this.productService.getAll(this.valueSearch, request).subscribe(data => {
      console.log(data);
      if (data !== undefined) {
        this.productList = data;
      }
    });
  }

  changePage(pageNumber: number): void {
    this.request.page = pageNumber;
    this.getAllProduct(this.request);
  }
}
