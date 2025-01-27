import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Quiz } from 'src/app/modules/quizz';
import { QuizService } from 'src/app/services/quizz.service';

@Component({
  selector: 'app-quiz',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './quiz.component.html',
  styleUrl: './quiz.component.scss'
})
export class QuizComponent implements OnInit {
  quizzes: Quiz[] = [];
  newQuiz: Quiz = {idQuiz: 0,
    question: '', reponseCorrecte: '', options: []
    
  };

  constructor(private quizService: QuizService) {}

  ngOnInit(): void {
    this.getAllQuizzes();
  }

  getAllQuizzes(): void {
    this.quizService.getAllQuizzes().subscribe(
      (data) => (this.quizzes = data),
      (error) => console.error(error)
    );
  }

  addQuiz(): void {
    this.quizService.addQuiz(this.newQuiz).subscribe(
      () => {
        this.getAllQuizzes();
        this.newQuiz = {idQuiz: 0, question: '', reponseCorrecte: '', options: [] };
      },
      (error) => console.error(error)
    );
  }

  deleteQuiz(id: number): void {
    this.quizService.deleteQuiz(id).subscribe(
      () => this.getAllQuizzes(),
      (error) => console.error(error)
    );
  }
}
