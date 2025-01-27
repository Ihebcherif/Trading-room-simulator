import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ordre } from '../modules/ordre';

@Injectable({
  providedIn: 'root',
})
export class OrdreService {
  private baseUrl = 'http://localhost:8089/tradingsim/api/ordres';

  constructor(private http: HttpClient) {}

  getAllOrdres(): Observable<Ordre[]> {
    return this.http.get<Ordre[]>(this.baseUrl);
  }

  getOrdreById(id: number): Observable<Ordre> {
    return this.http.get<Ordre>(`${this.baseUrl}/${id}`);
  }

  updateOrdre(id: number, ordre: Ordre): Observable<Ordre> {
    return this.http.put<Ordre>(`${this.baseUrl}/${id}`, ordre);
  }

  deleteOrdre(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  createOrder(ordre: Ordre): Observable<Ordre> {
    return this.http.post<Ordre>(`${this.baseUrl}/createorder`, ordre);
  }
}
