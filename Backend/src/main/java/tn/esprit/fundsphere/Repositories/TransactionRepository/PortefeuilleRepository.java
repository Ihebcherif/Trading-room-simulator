package tn.esprit.fundsphere.Repositories.TransactionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.TransactionManagement.Portefeuille;

public interface PortefeuilleRepository extends JpaRepository<Portefeuille,Long> {
}
