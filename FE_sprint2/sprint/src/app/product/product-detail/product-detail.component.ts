import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {ActivatedRoute} from '@angular/router';
import {Product} from '../../interface/product';
import {ImageProducts} from '../../interface/image-products';

const script = document.createElement('script');
script.src = '../../../assets/javascript/detail.js';
document.body.appendChild(script);

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productDetail: Product = {};
  idProduct = 0;

  imageList: ImageProducts[] | undefined = [];

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute) {
    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      if (id != null) {
        this.idProduct = Number(id);
        this.productService.findById(Number(id)).subscribe(dataProduct => {
          console.log(dataProduct);
          this.productDetail = dataProduct;
          this.imageList = dataProduct.imageProducts;
        });
      }
    });
  }

  ngOnInit(): void {
  }

}
