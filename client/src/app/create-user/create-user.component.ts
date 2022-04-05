import {Component, OnInit} from '@angular/core';
import {User} from '../models/users/user';
import {Router} from '@angular/router';
import {UserService} from '../models/users/user.service';
import {Pupil} from "../models/pupils/pupil";
import {UserDTO} from "../models/userDTO/userDTO";
import {UserDTOService} from "../models/userDTO/userDTO.service";

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
  userDTO: UserDTO = new UserDTO();
  check
  msgs

  constructor(private userService: UserService,
              private userDTOService: UserDTOService,
              private router: Router) {
  }

  ngOnInit() {
  }

  createUser(): void {
    this.submitted = false;
    this.user = new User();
    this.userDTO = new UserDTO();
  }

  // дописать, если такой найден, то создать
  save() {
    if (this.check) {
      this.userDTO.role = "pupil";
    } else {
      this.userDTO.role = "teacher";
    }
    this.userDTO.status = "unBlock";

    // возвращает в имени сущности строку с комментарием ошибки, если что-то не так
    // возвращает сущность, если всё так
    this.userDTOService.createUserDTO(this.userDTO)
      .subscribe(data => console.log(data), error => console.log(error));
    this.userDTO = new UserDTO();
    this.gotoMain();
  }

  onSubmit() {
    if (this.user.password == this.repeatPassword) {
      this.submitted = true;
      this.save();
    } else {
      this.msgs = "Пароли не совпадают";
    }
  }

  gotoMain() {
    this.router.navigate(['/main']);
  }
}
