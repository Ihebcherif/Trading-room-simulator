package tn.esprit.fundsphere.Controllers.FormationController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.FormationManagement.Formation;
import tn.esprit.fundsphere.Services.FormationService.IAService;
import tn.esprit.fundsphere.Services.FormationService.IFormationService;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationRestController {
    private final IFormationService formationService;
    @Autowired
    private IAService iaService;

    @PostMapping
    public ResponseEntity<Formation> addFormation(@RequestBody Formation formation) {
        return ResponseEntity.ok(formationService.addFormation(formation));
    }

    @GetMapping
    public ResponseEntity<List<Formation>> getAllFormations() {
        return ResponseEntity.ok(formationService.getAllFormations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable int id) {
        return formationService.getFormationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable int id, @RequestBody Formation formation) {
        return ResponseEntity.ok(formationService.updateFormation(id, formation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable int id) {
        formationService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/generateQuiz")
    public ResponseEntity<String> generateQuiz(@RequestBody String courseContent) {
        try {
            // Utilisation du service IA pour générer les questions de quiz
            String quiz = iaService.generateQuiz(courseContent);
            return ResponseEntity.ok(quiz); // Retourne les questions de quiz générées
        } catch (Exception e) {
            // En cas d'erreur, retourne une réponse avec un message d'erreur
            return ResponseEntity.status(500).body("Error generating quiz: " + e.getMessage());
        }
    }
}
