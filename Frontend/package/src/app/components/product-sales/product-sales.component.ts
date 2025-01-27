import { Component, OnInit,  } from '@angular/core';
import { TablerIconsModule } from 'angular-tabler-icons';
import { MaterialModule } from 'src/app/material.module';
import { MatButtonModule } from '@angular/material/button';
import { Quiz } from 'src/app/modules/quizz';
import { QuizService } from 'src/app/services/quizz.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-product-sales',
  standalone: true,
  imports: [MaterialModule, TablerIconsModule, MatButtonModule,CommonModule ,FormsModule],
  templateUrl: './product-sales.component.html',
})
export class AppProductSalesComponent implements OnInit {
  quizzes: Quiz[] = [];
  selectedQuiz: Quiz | null = null;
  isEditMode: boolean = false;

  constructor(private quizService: QuizService) {}

  ngOnInit(): void {
    this.loadQuizzes();
  }

  // Charger tous les quiz
  loadQuizzes(): void {
    this.quizService.getAllQuizzes().subscribe(
      (data) => {
        this.quizzes = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des quiz', error);
      }
    );
  }

  // Sélectionner un quiz pour modification
  selectQuiz(quiz: Quiz): void {
    this.selectedQuiz = { ...quiz };
    this.isEditMode = true;
  }

  // Ajouter un nouveau quiz
  addNewQuiz(): void {
    // Initialisation d'un objet Quiz vide
    this.selectedQuiz = {
      idQuiz: 0,
      question: '',
      reponseCorrecte: '',
      options: [],
    };
    this.isEditMode = false;  // Mode création
  }

  // Ajouter un quiz via le formulaire
  addQuiz(quiz: Quiz): void {
    this.quizService.addQuiz(quiz).subscribe(
      (data) => {
        this.quizzes.push(data);
        this.resetSelection();
      },
      (error) => {
        console.error('Erreur lors de l\'ajout du quiz', error);
      }
    );
  }

  // Mettre à jour un quiz existant
  updateQuiz(id: number, quiz: Quiz): void {
    this.quizService.addQuiz(quiz).subscribe(
      (data) => {
        const index = this.quizzes.findIndex((q) => q.idQuiz === id);
        if (index !== -1) {
          this.quizzes[index] = data;
        }
        this.resetSelection();
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du quiz', error);
      }
    );
  }

  // Supprimer un quiz
  deleteQuiz(id: number): void {
    this.quizService.deleteQuiz(id).subscribe(
      () => {
        this.quizzes = this.quizzes.filter((quiz) => quiz.idQuiz !== id);
      },
      (error) => {
        console.error('Erreur lors de la suppression du quiz', error);
      }
    );
  }

  // Réinitialiser la sélection
  resetSelection(): void {
    this.selectedQuiz = null;
    this.isEditMode = false;
  }
}