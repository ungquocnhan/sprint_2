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
import {ProductInfo} from '../../model/interface/product-info';
import {OwlOptions} from 'ngx-owl-carousel-o';


@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
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
  productDetail: Product | undefined;
  idProduct = 0;
  imageList1: string | undefined;
  total = 0;
  imageList: ImageProducts[] | undefined = [];
  cartDetail: CartDetail = {};
  quantity = 1;
  cartList: CartDto[] = [];
  productSameManufactureList: ProductInfo[] = [];

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
      this.productService.getProductSameManufacture(this.productDetail.manufacture?.name, this.productDetail.id).subscribe(data => {
        console.log(data);
        this.productSameManufactureList = data;
      });
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
      this.toastrService.success('Th??m v??o gi??? h??ng th??nh c??ng.', 'Th??ng b??o.');
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
