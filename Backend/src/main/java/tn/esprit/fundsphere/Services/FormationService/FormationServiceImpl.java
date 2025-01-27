package tn.esprit.fundsphere.Services.FormationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.FormationManagement.Formation;
import tn.esprit.fundsphere.Repositories.FormationRepository.FormationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class FormationServiceImpl implements IFormationService{
    private final FormationRepository formationRepository;

    // Ajouter une formation
    public Formation addFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    // Récupérer toutes les formations
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    // Récupérer une formation par ID
    public Optional<Formation> getFormationById(int id) {
        return formationRepository.findById(id);
    }

    // Mettre à jour une formation
    public Formation updateFormation(int id, Formation updatedFormation) {
        return formationRepository.findById(id).map(existingFormation -> {
            existingFormation.setTitle(updatedFormation.getTitle());
            existingFormation.setContent(updatedFormation.getContent());
            existingFormation.setDescription(updatedFormation.getDescription());
            existingFormation.setCertifiante(updatedFormation.isCertifiante());
            return formationRepository.save(existingFormation);
        }).orElseThrow(() -> new RuntimeException("Formation introuvable"));
    }

    // Supprimer une formation
    public void deleteFormation(int id) {
        formationRepository.deleteById(id);
    }
}
