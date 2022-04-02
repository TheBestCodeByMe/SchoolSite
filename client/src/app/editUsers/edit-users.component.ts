import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../models/users/user";
import {UserService} from "../models/users/user.service";
import {Router} from "@angular/router";
import {PupilDTO} from "../models/pupilDTO/pupilDTO";
import {PupilDTOService} from "../models/pupilDTO/pupilDTO.service";
import {Pupil} from "../models/pupils/pupil";
import {Parent} from "../models/parents/parent";
import {PupilService} from "../models/pupils/pupil.service";


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
  pupil: Pupil = new Pupil();
  parents: Parent = new Parent();
  fioMom
  fioDad

  constructor(private userService: UserService,
              private pupilDTOService: PupilDTOService,
              private pupilService: PupilService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersList();
    this.pupilDTOs = this.pupilDTOService.getPupilDTOsList();
  }

  savePupil() {
    const tempMom = this.fioMom.split(" ");
    this.parents.name_mom = tempMom[0];
    this.parents.lastname_mom = tempMom[1];
    this.parents.patronymic_mom = tempMom[2];
    const tempDad = this.fioDad.split(" ");
    this.parents.name_dad = tempDad[0];
    this.parents.lastname_dad = tempDad[1];
    this.parents.patronymic_dad = tempDad[2];
    this.pupilService.createPupil(this.pupil, this.parents)
      .subscribe(data => console.log(data), error => console.log(error));
    this.pupil = new Pupil();
    this.parents = new Parent();
  }
}
