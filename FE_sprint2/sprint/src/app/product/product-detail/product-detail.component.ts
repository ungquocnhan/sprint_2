import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {ActivatedRoute} from '@angular/router';
import {Product} from '../../model/interface/product';
import {ImageProducts} from '../../model/interface/image-products';
import {CartService} from '../../service/cart.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CartDetail} from '../../model/interface/cart-detail';
import {TokenService} from '../../service/token.service';
import {ToastrService} from 'ngx-toastr';
import {timeout} from 'rxjs/operators';
import {BehaviorSubject} from 'rxjs';
import {CartDto} from '../../model/interface/cart-dto';
import {SearchService} from '../../service/search.service';


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
  cartDetail: CartDetail = {};
  quantity = 1;
  cartList: CartDto[] = [];

  private static carouselDetailJs(): void {
    const script = document.createElement('script');
    script.src = 'assets/javascript/detail.js';
    document.body.appendChild(script);
  }

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService,
              private tokenService: TokenService,
              private toastrService: ToastrService,
              private searchService: SearchService) {

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
  }

  addToCart(): void {
    this.cartDetail.quantity = this.quantity;
    this.cartDetail.idProduct = this.productDetail?.id;
    this.cartDetail.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.addToCart(this.cartDetail).subscribe(() => {
      this.cartService.getCartByIdCustomer(Number(this.tokenService.getIdCustomer())).subscribe(data => {
        this.cartList = data;
        this.searchService.setCount(this.cartList.length);
      });
      this.toastrService.success('Thêm vào giỏ hàng thành công.', 'Thông báo.');
    });
  }

  decrease(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }

  increase(): void {
    if (this.quantity < 10) {
      this.quantity++;
    }
  }
}
