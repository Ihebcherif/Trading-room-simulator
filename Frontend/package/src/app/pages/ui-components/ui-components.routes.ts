import { Routes } from '@angular/router';

// ui

import { FormationComponent } from './formation/formation.component';
import { ActuarialComponent } from './actuarial/actuarial.component';
import { QuizComponent } from './quiz/quiz.component';
import { ConcoursComponent } from './concours/concours.component';
import { ContractComponent } from './contract/contract.component';
import { OrdreComponent } from './ordre/ordre.component';
import { PortefeuilleComponent } from './portefeuille/portefeuille.component';
import { PortfolioRiskComponent } from './portfolio-risk/portfolio-risk.component';
import { PredictionComponent } from './prediction/prediction.component';

export const UiComponentsRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'Formation',
        component: FormationComponent,
      },
      {
        path: 'Actuariat',
        component: ActuarialComponent,
      },
      {
        path: 'Quizz',
        component: QuizComponent,
      },
      {
        path: 'Concours',
        component: ConcoursComponent,
      },
      {
        path: 'Contract',
        component: ContractComponent,
      },
      {
        path: 'Ordre',
        component: OrdreComponent,
      },
      {
        path: 'PorteFeuille',
        component: PortefeuilleComponent,
      },
      {
        path: 'Portfolio-risk',
        component: PortfolioRiskComponent,
      },
      {
        path: 'Prediction',
        component: PredictionComponent,
      },
      
    ],
  },
];
