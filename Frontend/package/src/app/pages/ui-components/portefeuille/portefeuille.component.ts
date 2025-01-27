import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Portefeuille } from 'src/app/modules/portefeuille';
import { PortefeuilleService } from 'src/app/services/portefeuille.service';

@Component({
  selector: 'app-portefeuille',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './portefeuille.component.html',
  styleUrl: './portefeuille.component.scss'
})
export class PortefeuilleComponent implements OnInit {
  portefeuilles: Portefeuille[] = [];
  newPortefeuille: Portefeuille = { totalValue: 0, liquidity: 0 };

  constructor(private portefeuilleService: PortefeuilleService) {}

  ngOnInit(): void {
    this.getAllPortefeuilles();
  }

  getAllPortefeuilles(): void {
    this.portefeuilleService.getAllPortefeuilles().subscribe(
      (data) => (this.portefeuilles = data),
      (error) => console.error(error)
    );
  }

  addPortefeuille(): void {
    this.portefeuilleService.createPortefeuille(this.newPortefeuille).subscribe(
      () => {
        this.getAllPortefeuilles();
        this.newPortefeuille = { totalValue: 0, liquidity: 0 };
      },
      (error) => console.error(error)
    );
  }

  deletePortefeuille(id: number): void {
    this.portefeuilleService.deletePortefeuille(id).subscribe(
      () => this.getAllPortefeuilles(),
      (error) => console.error(error)
    );
  }
}
