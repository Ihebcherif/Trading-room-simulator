import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PortfolioRiskService } from 'src/app/services/risk.service';

@Component({
  selector: 'app-portfolio-risk',
  standalone: true,
  imports: [FormsModule,CommonModule],

  templateUrl: './portfolio-risk.component.html',
  styleUrl: './portfolio-risk.component.scss'
})
export class PortfolioRiskComponent implements OnInit {
  portfolioVaR: number | undefined;

  constructor(private portfolioRiskService: PortfolioRiskService) {}

  ngOnInit(): void {}

  calculateVaRFromInput(portfolioId: string, confidence: string): void {
    const parsedPortfolioId = parseInt(portfolioId, 10);
    const parsedConfidence = parseFloat(confidence);

    if (!isNaN(parsedPortfolioId) && !isNaN(parsedConfidence)) {
      this.calculateVaR(parsedPortfolioId, parsedConfidence);
    } else {
      console.error('Invalid input: Portfolio ID or Confidence Level is not a valid number.');
    }
  }

  private calculateVaR(portfolioId: number, confidence: number): void {
    this.portfolioRiskService.getPortfolioVaR(portfolioId, confidence).subscribe(
      (data) => (this.portfolioVaR = data),
      (error) => console.error(error)
    );
  }
}