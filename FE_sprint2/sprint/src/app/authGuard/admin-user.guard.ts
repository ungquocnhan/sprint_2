import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenService} from '../service/token.service';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AdminUserGuard implements CanActivate {
  constructor(private tokenService: TokenService,
              private router: Router,
              private toast: ToastrService) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.tokenService.getToken()) {
      if (JSON.stringify(this.tokenService.getRole()) === JSON.stringify(['ADMIN'])) {
        return true;
      } else {
        this.toast.warning('Bạn không đủ quyền, vui lòng đăng nhập để tiếp tục.', 'Thông báo');
        this.router.navigateByUrl('');
        return false;
      }
    } else {
      return false;
    }
  }

}
