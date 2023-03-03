import {Component, OnInit} from '@angular/core';
import {SecurityService} from '../../service/security.service';
import {TokenService} from '../../service/token.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  statusRole: any[] = [];
  // @ts-ignore
  signInForm: FormGroup;

  constructor(
    private securityService: SecurityService,
    private tokenService: TokenService,
    private router: Router,
    private toast: ToastrService,
    private formBuilder: FormBuilder,
    private titleService: Title
  ) {
    this.titleService.setTitle('Trang đăng nhập');
  }

  getFormLogin(): void {
    this.signInForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.minLength(3)]],
      encryptPassword: ['', [Validators.required, Validators.minLength(5)]],
      rememberMe: [false]
    });
  }

  ngOnInit(): void {
    this.getFormLogin();
  }

  login(): void {
    const signInForm = this.signInForm?.value;
    this.securityService.signIn(signInForm).subscribe(data => {
        console.log(data);
        if (data.token !== undefined) {
          if (this.signInForm?.value.rememberMe) {
            this.tokenService.rememberMe(data.roles, data.name, data.token);
            this.router.navigateByUrl('/header');
          } else {
            this.tokenService.setToken(data.token);
            this.tokenService.setName(data.name);
            this.tokenService.setRole(data.roles);
            this.tokenService.setAvatar(data.avatar);
            this.tokenService.setIdCustomer(data.idCustomer.idCustomer);
            this.statusRole = data.roles;
            this.tokenService.setEmail(data.email);
            this.tokenService.setId(data.id);
            location.href = 'http://localhost:4200/home';
            this.toast.info('Đăng nhập thành công.', 'Thông báo', {
              timeOut: 3000
            });
          }
        }
        // @ts-ignore
        if (data.status === 202) {
          this.toast.error('Mật khẩu không đúng vui lòng nhập lại.', 'Thông báo', {
            timeOut: 3000,
            extendedTimeOut: 1500
          });
        }
      }, error => {
        if (error.status === 403) {
          this.toast.error('Đăng nhập thất bại, vui lòng nhập lại.', 'Thông báo');
        }
      }
    )
    ;
  }

}
