import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {LoginInfo} from "../models/login-info/login-info";
import {Router} from "@angular/router";


@Component({
  selector: 'app-main',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css',
    './assets/bootstrap/css/bootstrap.min.css'
  ]
})
export class SignInComponent implements OnInit {
  autorization(){

  }

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: LoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit() {
    console.log('test this form');
    console.log(this.form);

    this.loginInfo = new LoginInfo(
      this.form.username,
      this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.log('1111111111');
        console.log(data);
        console.log(data.token);
        console.log(data.login);
        console.log(data.roles);
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.login);
        this.tokenStorage.saveAuthorities(data.roles);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );

    // Не работает
    if(this.roles == ["ROLE_TEACHER"]){
      this.gotoMenuTeacher()
    } else if(this.roles == ["ROLE_PUPIL"]){
      this.gotoMenuTeacher()
    } else if(this.roles == ["ROLE_DIRECTOR"]){
      this.gotoMenuTeacher()
    }
  }

  reloadPage() {
    window.location.reload();
  }

  gotoMenuTeacher() {
    this.router.navigate(['/menuTeacher']);
  }

  gotoMenuPupil() {
    this.router.navigate(['/menuPupil']);
  }

  gotoMenuDirector() {
    this.router.navigate(['/menuDirector']);
  }

}
