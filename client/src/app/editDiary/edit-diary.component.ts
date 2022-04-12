import {Component, OnInit} from '@angular/core';
import {DiaryDTO} from "../models/diaryDTO/diaryDTO";
import {DiaryDTOService} from "../models/diaryDTO/diaryDTO.service";


@Component({
  selector: 'app-main',
  templateUrl: './edit-diary.component.html',
  styleUrls: ['./edit-diary.component.css',
    './assets/fonts/font-awesome.min.css',
    './assets/fonts/fontawesome-all.min.css',
    './assets/fonts/fontawesome5-overrides.min.css',
    './assets/bootstrap/css/bootstrap.min.css'
  ]
})
export class EditDiaryComponent implements OnInit {

  diaryDTO: DiaryDTO = new DiaryDTO();
  fioPupil;
  check;

  ngOnInit() {
  }

  constructor(private diaryDTOService: DiaryDTOService) {
  }

  addGradle() {
    const temp = this.fioPupil.split(" ");
    this.diaryDTO.namePupil = temp[1];
    this.diaryDTO.lastnamePupil = temp[0];
    this.diaryDTO.patronymicPupil = temp[2];

    this.diaryDTOService.createAttendanceAndAcademicPerfomance(this.diaryDTO)
      .subscribe(data => console.log(data), error => console.log(error));
  }
}
