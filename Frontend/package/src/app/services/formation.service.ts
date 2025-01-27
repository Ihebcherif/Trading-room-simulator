import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Formation } from '../modules/formation';

@Injectable({
  providedIn: 'root',
})
export class FormationService {
  private baseUrl = 'http://localhost:8089/tradingsim/api/formations';

  constructor(private http: HttpClient) {}

  addFormation(formation: Formation): Observable<Formation> {
    return this.http.post<Formation>(this.baseUrl, formation);
  }

  getAllFormations(): Observable<Formation[]> {
    return this.http.get<Formation[]>(this.baseUrl);
  }

  getFormationById(id: number): Observable<Formation> {
    return this.http.get<Formation>(`${this.baseUrl}/${id}`);
  }

  updateFormation(id: number, formation: Formation): Observable<Formation> {
    return this.http.put<Formation>(`${this.baseUrl}/${id}`, formation);
  }

  deleteFormation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  generateQuiz(content: string): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/generateQuiz`, content);
  }
}
