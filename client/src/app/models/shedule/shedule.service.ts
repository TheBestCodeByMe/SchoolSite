import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SheduleService {
  private baseUrl = 'http://localhost:8080/api/v1/users';

  constructor(private http: HttpClient) { }

  getShedule(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createShedule(classroom: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, classroom);
  }

  updateShedule(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteShedule(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getShedulsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
