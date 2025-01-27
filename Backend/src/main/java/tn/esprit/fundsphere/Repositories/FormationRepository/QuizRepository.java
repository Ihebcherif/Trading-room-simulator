package tn.esprit.fundsphere.Repositories.FormationRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.FormationManagement.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
