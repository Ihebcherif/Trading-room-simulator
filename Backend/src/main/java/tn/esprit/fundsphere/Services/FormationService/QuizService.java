    package tn.esprit.fundsphere.Services.FormationService;

    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import tn.esprit.fundsphere.Entities.FormationManagement.Quiz;
    import tn.esprit.fundsphere.Repositories.FormationRepository.QuizRepository;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class QuizService implements IQuizService {
        private final QuizRepository quizRepository;

        // Ajouter un quiz
        public Quiz addQuiz(Quiz quiz) {
            return quizRepository.save(quiz);
        }

        // Récupérer tous les quiz
        public List<Quiz> getAllQuizzes() {
            return quizRepository.findAll();
        }

        // Supprimer un quiz
        public void deleteQuiz(int id) {
            quizRepository.deleteById(id);
        }
    }
