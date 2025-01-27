export interface Quiz {
  idQuiz: number;
  question: string;
  reponseCorrecte: string; // Correct answer
  options: string[]; // List of possible answers
  formationId?: number; // Associated formation ID
}
