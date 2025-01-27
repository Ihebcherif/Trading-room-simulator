import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Portefeuille } from '../modules/portefeuille';

@Injectable({
  providedIn: 'root',
})
export class PortefeuilleService {
  private baseUrl = 'http://localhost:8089/tradingsim/portfeuilles';

  constructor(private http: HttpClient) {}

  getAllPortefeuilles(): Observable<Portefeuille[]> {
    return this.http.get<Portefeuille[]>(this.baseUrl);
  }

  getPortefeuilleById(id: number): Observable<Portefeuille> {
    return this.http.get<Portefeuille>(`${this.baseUrl}/${id}`);
  }

  createPortefeuille(portefeuille: Portefeuille): Observable<Portefeuille> {
    return this.http.post<Portefeuille>(this.baseUrl, portefeuille);
  }

  updatePortefeuille(id: number, portefeuille: Portefeuille): Observable<Portefeuille> {
    return this.http.put<Portefeuille>(`${this.baseUrl}/${id}`, portefeuille);
  }

  deletePortefeuille(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
