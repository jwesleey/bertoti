import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VotesService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  criarvoto(voto: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/votar`, voto)
  }

  getVotesByEmail(email: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/meuvoto`, { params: { email_user: email } });
  }

  updateVote(vote: { emailUser: string, criticVote: string }): Observable<any> {
    const data = {
      email_user: vote.emailUser,
      critic_voto: vote.criticVote
    };
    return this.http.put(`${this.apiUrl}/editar`, data)
  }

  deleteVote(email: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete`, { body: email, headers: { 'Content-Type': 'text/plain' } });
  }

  getVotesRanking(): Observable<{ name_mov: string, vote_count: number }[]> {
    return this.http.get<{ name_mov: string, vote_count: number }[]>(`${this.apiUrl}/ranking`);
  }
}