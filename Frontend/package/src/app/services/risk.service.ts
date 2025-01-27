import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PortfolioRiskService {
  private baseUrl = 'http://localhost:8089/tradingsim/portfoliorisk';

  constructor(private http: HttpClient) {}

  getPortfolioVaR(idPortefeuille: number, confidence: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/portfolio-var/${idPortefeuille}/${confidence}`);
  }
}
