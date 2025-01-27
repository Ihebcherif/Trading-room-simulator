package tn.esprit.fundsphere.Services.FormationService;

import tn.esprit.fundsphere.Entities.FormationManagement.Quiz;

import java.util.List;

public interface IQuizService {
    Quiz addQuiz(Quiz quiz);
    List<Quiz> getAllQuizzes() ;
    void deleteQuiz(int id);

}

