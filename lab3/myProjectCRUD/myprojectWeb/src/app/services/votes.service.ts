import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VotesService {
  private apiUrl = 'http://localhost:8080/api/votos';

  constructor(private http: HttpClient) { }

  criarvoto(voto: any): Observable<any> {
    return this.http.post(this.apiUrl, voto)
   }
}
