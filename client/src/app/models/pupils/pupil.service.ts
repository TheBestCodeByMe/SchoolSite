import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PupilService {
  private baseUrl = 'http://localhost:8080/api/v1/editUsers';

  constructor(private http: HttpClient) { }

  getPupil(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createPupil(pupil: Object, parents: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/createPupil`, pupil, parents);
  }

  updatePupil(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePupil(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getPupilsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
