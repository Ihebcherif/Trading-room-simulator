import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contract } from '../modules/contract';

@Injectable({
  providedIn: 'root',
})
export class ContractService {
  private baseUrl = 'http://localhost:8089/tradingsim/contract';

  constructor(private http: HttpClient) {}

  addContract(contract: Contract): Observable<Contract> {
    return this.http.post<Contract>(`${this.baseUrl}/add-credit`, contract);
  }

  getAllContracts(): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.baseUrl}/show-credits`);
  }

  deleteContract(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete-credit/${id}`);
  }

  updateContract(id: number, contract: Contract): Observable<Contract> {
    return this.http.put<Contract>(`${this.baseUrl}/update-contract/${id}`, contract);
  }

  getContractById(id: number): Observable<Contract> {
    return this.http.get<Contract>(`${this.baseUrl}/get-contract/${id}`);
  }

  calculatePrime(idPortefeuille: number, confidence: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/calculate-prime/${idPortefeuille}/${confidence}`);
  }

  backtestStrategy(idPortefeuille: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/strategy/${idPortefeuille}`);
  }
}
