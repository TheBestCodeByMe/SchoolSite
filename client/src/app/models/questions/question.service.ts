import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) { }

  getQuestion(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createQuestion(question: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/askQuestion/addQuestion`, question);
  }

  updateQuestion(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteQuestion(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getQuestionsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
