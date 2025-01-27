import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PredictionService {
  private baseUrl = 'http://localhost:8089/tradingsim/api/prediction';

  constructor(private http: HttpClient) {}

  getPrediction(): Observable<string> {
    return this.http.get<string>(this.baseUrl);
  }
}
