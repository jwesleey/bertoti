import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getTest(): Observable<string> {
    return this.http.get<string>(`${this.apiUrl}/test`, {responseType: 'text' as 'json'});
  }
}
