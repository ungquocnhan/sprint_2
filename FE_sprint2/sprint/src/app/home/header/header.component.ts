import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
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
}
