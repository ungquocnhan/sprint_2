import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../../service/token.service';
import {SearchService} from '../../service/search.service';
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
  valueSearch = '';
  constructor(private router: Router,
              private toast: ToastrService,
              private tokenService: TokenService,
              private searchService: SearchService) {
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.name = this.tokenService.getName();
      this.roles = this.tokenService.getRole();
      this.idAccount = this.tokenService.getId();
    }
  }

  logOut(): void {
    window.localStorage.clear();
    this.router.navigateByUrl('/').then(() => {
      location.reload();
    });
    this.toast.info('Đăng xuất thành công.', ' Thông báo', {
      timeOut: 3000
    });
  }

  search(searchInput: string): void {
    this.valueSearch = searchInput;
    console.log(this.valueSearch);
    this.searchService.setValue(this.valueSearch);
  }
}
