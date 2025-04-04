import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:4200';

  constructor(private http: HttpClient) { }


  fazerRequisicao(): Observable<any> {
    return this.http.get(this.apiUrl); //GET
  }

  enviarDados(dados: any): Observable<any> {
    return this.http.post(this.apiUrl, dados); //POST
  }

  criarvoto(vote: any): Observable<any> {
    return this.http.post(this.apiUrl, vote)
  }

  getTest(): Observable<string> {
    return this.http.get<string>(`${this.apiUrl}/test`, { responseType: 'text' as 'json' });
  }
}
