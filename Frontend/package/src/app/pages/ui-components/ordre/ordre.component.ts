import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Ordre } from 'src/app/modules/ordre';
import { OrdreService } from 'src/app/services/ordre.service';

@Component({
  selector: 'app-ordre',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './ordre.component.html',
  styleUrl: './ordre.component.scss'
})
export class OrdreComponent implements OnInit {
  ordres: Ordre[] = [];
  newOrdre: Ordre = { typeOrdre: 'BUY', dateOrdre: '', unitPrice: 0, status: false, quantity: 0, actif: 'FOREX' };

  constructor(private ordreService: OrdreService) {}

  ngOnInit(): void {
    this.getAllOrdres();
  }

  getAllOrdres(): void {
    this.ordreService.getAllOrdres().subscribe(
      (data) => (this.ordres = data),
      (error) => console.error(error)
    );
  }

  addOrdre(): void {
    this.ordreService.createOrder(this.newOrdre).subscribe(
      () => {
        this.getAllOrdres();
        this.newOrdre = { typeOrdre: 'BUY', dateOrdre: '', unitPrice: 0, status: false, quantity: 0, actif: 'FOREX' };
      },
      (error) => console.error(error)
    );
  }

  deleteOrdre(id: number): void {
    this.ordreService.deleteOrdre(id).subscribe(
      () => this.getAllOrdres(),
      (error) => console.error(error)
    );
  }
}