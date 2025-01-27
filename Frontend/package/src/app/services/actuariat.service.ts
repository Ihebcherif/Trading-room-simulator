import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CashFlow } from '../modules/cashflow';

@Injectable({
  providedIn: 'root',
})
export class ActuarialService {
  private baseUrl = 'http://localhost:8089/tradingsim/actuarial';

  constructor(private http: HttpClient) {}

  calculateRiskAdjustedNPV(cashFlows: CashFlow[], discountRate: number): Observable<number> {
    return this.http.post<number>(`${this.baseUrl}/calculate-npv`, cashFlows, {
      params: { discountRate: discountRate.toString() },
    });
  }
}
