import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../../service/token.service';
import {SearchService} from '../../service/search.service';
import {ProductService} from '../../service/product.service';
import {Manufacture} from '../../model/interface/manufacture';
import {CartService} from '../../service/cart.service';

const script = document.createElement('script');
script.src = '../../../assets/javascript/header.js';
document.body.appendChild(script);

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  checkLogin = false;
  name: string | null | undefined;
  roles: string[] = [];
  idAccount: string | null | undefined;
  manufactureList: Manufacture[] = [];
  avatar: string | null | undefined;
  itemCount = 0;
  @ViewChild('searchInput') searchInput: any;

  constructor(private router: Router,
              private toast: ToastrService,
              private tokenService: TokenService,
              private searchService: SearchService,
              private productService: ProductService) {
    this.searchService.getCount().subscribe(count => {
      this.itemCount = count;
    });
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.name = this.tokenService.getName();
      this.roles = this.tokenService.getRole();
      this.idAccount = this.tokenService.getId();
      this.avatar = this.tokenService.getAvatar();
    }
    this.productService.getAllManufacture().subscribe(data => {
      this.manufactureList = data;
    });
  }


  logOut(): void {
    window.sessionStorage.clear();
    this.router.navigateByUrl('/').then(() => {
      location.reload();
    });
    this.toast.info('Đăng xuất thành công.', ' Thông báo', {
      timeOut: 3000
    });
  }

  search(searchInput: string): void {
    this.searchService.setValue(searchInput);
    this.router.navigateByUrl('/home');
    this.searchInput.nativeElement.value = '';
  }

  developing(): void {
    this.toast.info('Chức năng đang được phát triển.', 'Xin lỗi.');
  }
}
