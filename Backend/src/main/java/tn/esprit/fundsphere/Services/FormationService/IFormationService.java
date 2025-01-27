package tn.esprit.fundsphere.Services.FormationService;

import tn.esprit.fundsphere.Entities.FormationManagement.Formation;

import java.util.List;
import java.util.Optional;

public interface IFormationService {
    Formation addFormation(Formation formation);
    List<Formation> getAllFormations();
    Optional<Formation> getFormationById(int id);
    Formation updateFormation(int id, Formation updatedFormation);
    void deleteFormation(int id);
}
