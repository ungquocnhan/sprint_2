import {Component, OnInit} from '@angular/core';
import {SecurityService} from '../../service/security.service';
import {Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TokenService} from '../../service/token.service';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';
import {Customer} from '../../model/interface/customer';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  submitted = false;
  action = true;
  status = false;
  account: Account | undefined;
  customer: Customer = {};
  customerRegisterForm: FormGroup = new FormGroup({});
  checkLogin = false;
  name: string | null | undefined;
  roles: string[] = [];
  idAccount: string | null | undefined;
  nameRegex = '^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*$';

  constructor(private securityService: SecurityService,
              private router: Router,
              private formBuilder: FormBuilder,
              private tokenService: TokenService,
              private toast: ToastrService,
              private title: Title) {
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.name = this.tokenService.getName();
      this.roles = this.tokenService.getRole();
      this.idAccount = this.tokenService.getId();
    }
    // tslint:disable-next-line:only-arrow-functions typedef
    window.onbeforeunload = function(e: Event | undefined) {
      e = e || window.event;
      // For IE and Firefox prior to version 4
      if (e) {
        e.returnValue = Boolean('Sure?');
      }
      // For Safari
      return 'Sure?';
    };

    this.customerRegisterForm = new FormGroup(
      {
        name: new FormControl('', [Validators.required, Validators.pattern(this.nameRegex),
          Validators.minLength(3), Validators.maxLength(50)]),
        email: new FormControl('', [Validators.required,
          Validators.pattern('^[a-zA-Z0-9.!#$%&\'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$')]),
        address: new FormControl('', [Validators.required, Validators.maxLength(255), Validators.minLength(5), Validators.maxLength(100)]),
        idNumber: new FormControl('', [Validators.required,
          Validators.pattern('^(\\d{12})$')]),
        gender: new FormControl('', Validators.required),
        birthday: new FormControl('', [Validators.required]),
        phoneNumber: new FormControl('', [Validators.required, Validators.pattern('(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})')]),
        encryptPassword: new FormControl('', [Validators.required,
          Validators.minLength(6), Validators.maxLength(50)])
      }, {validators: this.checkBirthday}
    );
  }

  checkBirthday(abstractControl: AbstractControl): any {
    // @ts-ignore
    const birthday = new Date(abstractControl.get('birthday').value).getTime();
    const dateNow = new Date().getTime();
    if (dateNow - birthday < 18 * 365 * 24 * 60 * 60 * 1000 || dateNow - birthday > 65 * 365 * 24 * 60 * 60 * 1000) {
      return {checkBirthDay: true};
    } else {
      return null;
    }
  }

  resetForm(): void {
    this.ngOnInit();
  }

  submit(): void {
    this.submitted = true;
    this.customer = this.customerRegisterForm?.value;
    this.securityService.register(this.customer).subscribe(value => {
      this.toast.success('Đăng ký thành công.');
      this.router.navigateByUrl('/security/signin');
    }, error => {
      this.action = false;
      this.toast.error('Đăng ký không thành công.', 'Thông báo');
    }, () => {
    });
  }
}
