import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiaryDTOService {
  private baseUrl = 'http://localhost:8080/api/v1/diary';

  constructor(private http: HttpClient) {
  }

  createAttendanceAndAcademicPerfomance(attendance: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/addAttendanceAndAcademicPerfomance`, attendance);
  }
}
