import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {ActivatedRoute} from '@angular/router';
import {Product} from '../../model/interface/product';
import {ImageProducts} from '../../model/interface/image-products';
import {CartService} from '../../service/cart.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {CartDetail} from '../../model/interface/cart-detail';
import {TokenService} from '../../service/token.service';


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
  addToCartForm: FormGroup = new FormGroup({});
  cartDetail: CartDetail = {};

  private static carouselDetailJs(): void {
    const script = document.createElement('script');
    script.src = 'assets/javascript/detail.js';
    document.body.appendChild(script);
  }

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService,
              private formBuilder: FormBuilder,
              private tokenService: TokenService) {

    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      if (id != null) {
        this.idProduct = Number(id);
        this.getProductDetail(id);
        this.getImageProduct(id);
      }
    });

  }

  private getImageProduct(id: string): void {
    this.productService.getImageProductById(Number(id)).subscribe(data => {
      this.imageList = data;
      this.imageList1 = data[0].url;
    });
  }


  private getProductDetail(id: string): void {
    this.productService.findById(Number(id)).subscribe(dataProduct => {
      this.productDetail = dataProduct;
      // @ts-ignore
      this.total = dataProduct.price - dataProduct.price * dataProduct.promotion?.percentPromotion;
      ProductDetailComponent.carouselDetailJs();
    });
  }

  ngOnInit(): void {
    this.addToCartForm = this.formBuilder.group({
      amount: ['']
    });
    // const script1 = document.createElement('script');
    // script1.src = '../../../assets/javascript/plus-minus.js';
    // document.body.appendChild(script1);
  }

  addToCart(): void {
    this.cartDetail.amount = Number(this.addToCartForm.value.amount);
    this.cartDetail.idProduct = this.productDetail?.id;
    this.cartDetail.idAccount = Number(this.tokenService.getId());
    console.log(this.cartDetail);
    this.cartService.addToCart(this.cartDetail).subscribe(() => {

    });
  }
}
