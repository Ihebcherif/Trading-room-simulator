package tn.esprit.fundsphere.Repositories.AssuranceRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.fundsphere.Entities.AssuranceManagement.CashFlow;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Integer> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}
