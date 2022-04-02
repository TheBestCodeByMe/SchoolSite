import {Component, OnInit} from '@angular/core';
import {User} from '../models/users/user';
import {Router} from '@angular/router';
import {UserService} from '../models/users/user.service';
import {Pupil} from "../models/pupils/pupil";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css',
    '/assets/bootstrap/css/bootstrap.min.css',
    '/assets/css/Registration-Form-with-Photo.css',
    '/assets/css/styles.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  submitted = false;
  repeatPassword
  pupil: Pupil = new Pupil();
  check

  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
  }

  createUser(): void {
    this.submitted = false;
    this.user = new User();
  }

  // дописать, если такой найден, то создать
  save() {
    if (this.check) {
      this.user.role = "pupil";
    } else {
      this.user.role = "teacher";
    }
    this.user.status = "unBlock";
    this.userService.createUser(this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
    this.gotoMain();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoMain() {
    this.router.navigate(['/main']);
  }
}
