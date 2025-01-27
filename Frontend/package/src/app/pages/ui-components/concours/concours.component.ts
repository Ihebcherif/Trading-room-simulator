import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Concours } from 'src/app/modules/concours';
import { ConcoursService } from 'src/app/services/concours.service';

@Component({
  selector: 'app-concours',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './concours.component.html',
  styleUrl: './concours.component.scss'
})
export class ConcoursComponent implements OnInit {
  concoursList: Concours[] = [];
  newConcours: Concours = { name: '', startDate: '', endDate: '' };

  constructor(private concoursService: ConcoursService) {}

  ngOnInit(): void {
    this.loadConcours();
  }

  loadConcours(): void {
    this.concoursService.getAllConcours().subscribe(
      (data) => (this.concoursList = data),
      (error) => console.error(error)
    );
  }

  addConcours(): void {
    this.concoursService.addConcours(this.newConcours).subscribe(
      () => {
        this.loadConcours();
        this.newConcours = { name: '', startDate: '', endDate: '' };
      },
      (error) => console.error(error)
    );
  }

  deleteConcours(id: number): void {
    this.concoursService.deleteConcours(id).subscribe(
      () => this.loadConcours(),
      (error) => console.error(error)
    );
  }
}
