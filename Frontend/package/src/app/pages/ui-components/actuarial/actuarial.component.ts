import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CashFlow } from 'src/app/modules/cashflow';
import { ActuarialService } from 'src/app/services/actuariat.service';

@Component({
  selector: 'app-actuarial',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './actuarial.component.html',
  styleUrl: './actuarial.component.scss'
})
export class ActuarialComponent implements OnInit {
  cashFlows: CashFlow[] = [];
  discountRate: number = 0.05;
  npv: number | undefined;

  constructor(private actuarialService: ActuarialService) {}

  ngOnInit(): void {
    this.cashFlows = [
      { amount: 1000, year: 1 },
      { amount: 2000, year: 2 },
    ];
  }

  calculateNPV(): void {
    this.actuarialService.calculateRiskAdjustedNPV(this.cashFlows, this.discountRate).subscribe(
      (data) => (this.npv = data),
      (error) => console.error(error)
    );
  }
}
