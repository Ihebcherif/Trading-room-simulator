import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Contract } from 'src/app/modules/contract';
import { Portefeuille } from 'src/app/modules/portefeuille';
import { ContractService } from 'src/app/services/contract.service';
import { PortefeuilleService } from 'src/app/services/portefeuille.service';

@Component({
  selector: 'app-contract',
  standalone: true,
 imports: [FormsModule,CommonModule],
  templateUrl: './contract.component.html',
  styleUrl: './contract.component.scss'
})
export class ContractComponent implements OnInit {
  contracts: Contract[] = [];
  portfolios: Portefeuille[] = [];
  selectedPortfolioId: number | undefined;
  newContract: Contract = { coverage: 0, prime: 0, startDate: '', endDate: '', policy: 'PENDING' };
  calculatedPrime: number | undefined;

  constructor(
    private contractService: ContractService,
    private portefeuilleService: PortefeuilleService
  ) {}

  ngOnInit(): void {
    this.getAllContracts();
    this.getAllPortfolios();
  }

  getAllContracts(): void {
    this.contractService.getAllContracts().subscribe(
      (data) => (this.contracts = data),
      (error) => console.error(error)
    );
  }

  getAllPortfolios(): void {
    this.portefeuilleService.getAllPortefeuilles().subscribe(
      (data) => (this.portfolios = data),
      (error) => console.error(error)
    );
  }

  addContract(): void {
    this.contractService.addContract(this.newContract).subscribe(
      () => {
        this.getAllContracts();
        this.newContract = { coverage: 0, prime: 0, startDate: '', endDate: '', policy: 'PENDING' };
      },
      (error) => console.error(error)
    );
  }

  deleteContract(id: number): void {
    this.contractService.deleteContract(id).subscribe(
      () => this.getAllContracts(),
      (error) => console.error(error)
    );
  }

  calculatePrimeFromInput(confidence: string): void {
    const confidenceValue = parseFloat(confidence);
    if (!isNaN(confidenceValue) && this.selectedPortfolioId) {
      this.calculatePrime(confidenceValue);
    } else {
      console.error('Invalid confidence value or no portfolio selected!');
    }
  }
  
  calculatePrime(confidence: number): void {
    if (!this.selectedPortfolioId) {
      console.error('No portfolio selected!');
      return;
    }
  
    this.contractService.calculatePrime(this.selectedPortfolioId, confidence).subscribe(
      (data) => (this.calculatedPrime = data),
      (error) => console.error(error)
    );
  }
}