import {Component, OnInit} from '@angular/core';
import {UserDTO} from "../models/userDTO/userDTO";


@Component({
  selector: 'app-main',
  templateUrl: './update-users.component.html',
  styleUrls: ['./update-users.component.css',
    './assets/bootstrap/css/bootstrap.min.css',
    './assets/css/Registration-Form-with-Photo.css',
    './assets/css/styles.css'
  ]
})
export class UpdateUsersComponent implements OnInit {

  userDTO: UserDTO = new UserDTO();

  ngOnInit() {
  }

  updateUser() {

  }

}
