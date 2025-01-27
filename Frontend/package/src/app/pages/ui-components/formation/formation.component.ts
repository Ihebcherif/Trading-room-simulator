import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatBadgeModule } from '@angular/material/badge';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { TablerIconsModule } from 'angular-tabler-icons';
import { Formation } from 'src/app/modules/formation';
import { FormationService } from 'src/app/services/formation.service';


@Component({
  selector: 'app-formation',
  standalone: true,
  imports: [MatCardModule, MatChipsModule, TablerIconsModule, MatButtonModule, MatIconModule,CommonModule,FormsModule],
  templateUrl: './formation.component.html',
  styleUrl: './formation.component.scss'
})
export class FormationComponent implements OnInit {
  formations: Formation[] = [];
  selectedFormation: Formation | null = null;
  isEditMode: boolean = false;

  constructor(private formationService: FormationService) {}

  ngOnInit(): void {
    this.loadFormations();
  }

  loadFormations(): void {
    this.formationService.getAllFormations().subscribe(
      (data) => {
        this.formations = data;
      },
      (error) => {
        console.error('Error loading formations:', error);
      }
    );
  }

  selectFormation(formation: Formation): void {
    this.selectedFormation = { ...formation };  // Clone the formation to prevent reference issues
    this.isEditMode = true;
  }

  addFormation(newFormation: Formation): void {
    this.formationService.addFormation(newFormation).subscribe(
      (data) => {
        this.formations.push(data);
      },
      (error) => {
        console.error('Error adding formation:', error);
      }
    );
  }

  updateFormation(id: number, updatedFormation: Formation): void {
    this.formationService.updateFormation(id, updatedFormation).subscribe(
      (data) => {
        const index = this.formations.findIndex(f => f.idFormation === id);
        if (index !== -1) {
          this.formations[index] = data;
        }
      },
      (error) => {
        console.error('Error updating formation:', error);
      }
    );
  }

  deleteFormation(id: number): void {
    this.formationService.deleteFormation(id).subscribe(
      () => {
        this.formations = this.formations.filter(f => f.idFormation !== id);
      },
      (error) => {
        console.error('Error deleting formation:', error);
      }
    );
  }

  resetSelection(): void {
    this.selectedFormation = {
      idFormation: 0, // On peut mettre 0 pour l'ID car il n'existe pas encore
      title: '',
      content: '',
      description: '',
      certifiante: false,
      niveauDeDifficulte: 'Facile', 
    };
    this.isEditMode = false;
  }
}
