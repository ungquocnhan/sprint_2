import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {ActivatedRoute} from '@angular/router';
import {Product} from '../../model/interface/product';
import {ImageProducts} from '../../model/interface/image-products';


@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productDetail: Product | undefined;
  idProduct = 0;
  imageList1: string | undefined;
  total = 0;
  imageList: ImageProducts[] | undefined = [];

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute) {

    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      if (id != null) {
        this.idProduct = Number(id);
        this.productService.findById(Number(id)).subscribe(dataProduct => {
          console.log(dataProduct);
          if (dataProduct !== undefined) {
            this.productDetail = dataProduct;
            // @ts-ignore
            this.total = dataProduct.price - dataProduct.price * dataProduct.promotion?.percentPromotion;
            if (dataProduct.imageProducts !== undefined) {
              this.imageList = dataProduct.imageProducts;
              console.log(this.imageList);
              this.imageList1 = dataProduct.imageProducts[0].url;
            }
          }
        });
      }
    });
  }

  ngOnInit(): void {
    const script = document.createElement('script');
    script.src = '../../../assets/javascript/detail.js';
    document.body.appendChild(script);
  }
}
