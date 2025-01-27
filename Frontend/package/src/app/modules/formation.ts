export interface Formation {
  idFormation: number;
  title: string;
  content: string; // Could be text, HTML, or Markdown
  description: string;
  certifiante: boolean; // Indicates if the training gives certification
  niveauDeDifficulte?: string; // Example: "Beginner", "Intermediate", "Advanced"
}
