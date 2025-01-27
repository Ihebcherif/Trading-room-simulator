import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Concours } from '../modules/concours';

@Injectable({
  providedIn: 'root',
})
export class ConcoursService {
  private baseUrl = 'http://localhost:8089/tradingsim/Concours';

  constructor(private http: HttpClient) {}

  addConcours(concours: Concours): Observable<Concours> {
    return this.http.post<Concours>(`${this.baseUrl}/add-Concours`, concours);
  }

  getAllConcours(): Observable<Concours[]> {
    return this.http.get<Concours[]>(`${this.baseUrl}/show-Concours`);
  }

  deleteConcours(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete-concours/${id}`);
  }

  updateConcours(concours: Concours): Observable<Concours> {
    return this.http.put<Concours>(`${this.baseUrl}/update-concours`, concours);
  }
}
