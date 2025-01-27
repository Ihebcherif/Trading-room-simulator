import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PredictionService } from 'src/app/services/prediction.service';

@Component({
  selector: 'app-prediction',
  standalone: true,
  imports: [FormsModule,CommonModule],

  templateUrl: './prediction.component.html',
  styleUrl: './prediction.component.scss'
})
export class PredictionComponent implements OnInit {
  prediction: string | undefined;

  constructor(private predictionService: PredictionService) {}

  ngOnInit(): void {}
  predictionImagePath: string | undefined;
  loadPrediction(): void {
    this.predictionImagePath = 'assets/images/output.png';
  }
}
