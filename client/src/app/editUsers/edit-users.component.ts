import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../models/users/user";
import {UserService} from "../models/users/user.service";
import {Router} from "@angular/router";
import {PupilDTO} from "../models/pupilDTO/pupilDTO";
import {PupilDTOService} from "../models/pupilDTO/pupilDTO.service";


@Component({
  selector: 'app-main',
  templateUrl: './edit-users.component.html',
  styleUrls: ['./edit-users.component.css',
    './assets/bootstrap/css/bootstrap.min.css',
    './assets/css/styles.css',
    './assets/css/Registration-Form-with-Photo.css'
  ]
})
export class EditUsersComponent implements OnInit {

  users: Observable<User[]>;
  pupilDTOs: Observable<PupilDTO[]>;

  constructor(private userService: UserService,
              private pupilDTOService: PupilDTOService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersList();
    this.pupilDTOs = this.pupilDTOService.getPupilDTOsList();
  }
}
