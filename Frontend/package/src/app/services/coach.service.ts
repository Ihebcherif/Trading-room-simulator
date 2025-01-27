import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TradingCoachService {
  private baseUrl = 'http://localhost:8089/tradingsim/api/trading-coach';

  constructor(private http: HttpClient) {}

  getStockPrice(symbol: string): Observable<string> {
    return this.http.get<string>(`${this.baseUrl}/stock-price`, {
      params: { symbol },
    });
  }

  getMarketSummary(): Observable<string> {
    return this.http.get<string>(`${this.baseUrl}/market-summary`);
  }

  askQuestion(question: string): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/ask-question`, { question });
  }
}
