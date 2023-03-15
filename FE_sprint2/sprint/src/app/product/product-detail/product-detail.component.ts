import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
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
import {Title} from '@angular/platform-browser';


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
  idProduct: string | undefined;
  imageDetail: string | undefined;
  imageType: string | undefined;
  total = 0;
  imageList: ImageProducts[] | undefined = [];
  cartDetail: CartDetail = {};
  quantity = 1;
  cartList: CartDto[] = [];
  productSameManufactureList: ProductInfo[] = [];
  checkLogin = false;
  message = '';

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
              private searchService: SearchService,
              private router: Router,
              private title: Title) {

    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      if (id != null) {
        this.idProduct = id;
        this.getProductDetail(id);
        this.getImageProduct(id);
      }
    });

    this.title.setTitle('Chi tiết sản phẩm');
  }

  private getImageProduct(id: string): void {
    this.productService.getImageProductById(Number(id)).subscribe(data => {
      this.imageList = data;
      this.imageDetail = data[0].url;
    });
  }


  private getProductDetail(id: string | undefined): void {
    this.productService.findById(Number(id)).subscribe(dataProduct => {
      this.productDetail = dataProduct;
      // @ts-ignore
      this.total = dataProduct.price - dataProduct.price * dataProduct.promotion?.percentPromotion;
      ProductDetailComponent.carouselDetailJs();
      this.productService.getProductSameManufacture(this.productDetail.manufacture?.name, this.productDetail.id).subscribe(data => {
        this.productSameManufactureList = data;
        this.imageType = data.imageProducts[0].url;
      });
    });
  }

  ngOnInit(): void {
  }

  addToCart(): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      // @ts-ignore
      if (this.quantity >= this.productDetail?.amountExist) {
        this.message = 'Số lượng bạn nhập lớn hơn số lượng sản phẩm còn trong kho.';
        this.getProductDetail(this.idProduct);
      } else if (this.quantity < 0) {
        this.message = 'Số lượng bạn nhập phải lớn hơn 0.';
        this.getProductDetail(this.idProduct);
      } else if (isNaN(this.quantity)) {
        this.message = 'Bạn không được nhập chữ vào đây.';
        this.getProductDetail(this.idProduct);
      } else {
        this.cartDetail.quantity = this.quantity;
        this.cartDetail.idProduct = this.productDetail?.id;
        this.cartDetail.idCustomer = Number(this.tokenService.getIdCustomer());
        // @ts-ignore
        // tslint:disable-next-line:max-line-length
        this.cartDetail.pricePromotion = Number(this.productDetail?.price - this.productDetail?.price * this.productDetail?.promotion?.percentPromotion);
        this.cartService.addToCart(this.cartDetail).subscribe(() => {
          this.cartService.getCartByIdCustomer(Number(this.tokenService.getIdCustomer())).subscribe(data => {
            this.cartList = data;
            this.searchService.setCount(this.cartList.length);
          });
          this.toastrService.success('Thêm vào giỏ hàng thành công.', 'Thông báo.');
        });
      }
    } else {
      this.toastrService.warning('Bạn phải đăng nhập để tiếp tục.', 'Thông báo.');
      this.router.navigateByUrl('/security/signin');
    }
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
