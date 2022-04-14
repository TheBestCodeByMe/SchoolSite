import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {DiaryDTO} from "../models/diaryDTO/diaryDTO";
import {DiaryDTOService} from "../models/diaryDTO/diaryDTO.service";


@Component({
  selector: 'app-main',
  templateUrl: './aboutStudent.component.html',
  styleUrls: ['./aboutStudent.component.css',
    './assets/fonts/font-awesome.min.css',
    './assets/fonts/fontawesome-all.min.css',
    './assets/fonts/fontawesome5-overrides.min.css'
  ]
})
export class AboutStudentComponent implements OnInit {

  classForSearch;
  students: Observable<DiaryDTO>;

  constructor(private diaryDTOService: DiaryDTOService) {
  }

  ngOnInit() {
  }

  search() {
   this.students = this.diaryDTOService.getInfoPupil(this.classForSearch);
  }
}
