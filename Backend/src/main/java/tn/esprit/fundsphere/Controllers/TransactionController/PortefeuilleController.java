package tn.esprit.fundsphere.Controllers.TransactionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.TransactionManagement.Portefeuille;
import tn.esprit.fundsphere.Services.TransactionService.PortefeuilleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/portfeuilles")
public class PortefeuilleController {
    @Autowired
    private PortefeuilleService portfeuilleService;

    @GetMapping
    public List<Portefeuille> getAllPortefeuilles() {
        return portfeuilleService.getAllPortefeuilles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Portefeuille> getPortefeuilleById(@PathVariable Long id) {
        Optional<Portefeuille> portefeuille = portfeuilleService.getPortefeuilleById(id);
        return portefeuille.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Portefeuille> createPortefeuille(@RequestBody Portefeuille portefeuille) {
        Portefeuille newPortefeuille = portfeuilleService.createPortefeuille(portefeuille);
        return ResponseEntity.ok(newPortefeuille);
    }

    // Mettre à jour un portefeuille existant
    @PutMapping("/{id}")
    public ResponseEntity<Portefeuille> updatePortefeuille(@PathVariable Long id, @RequestBody Portefeuille portefeuille) {
        Portefeuille updatedPortefeuille = portfeuilleService.updatePortefeuille(id, portefeuille);
        if (updatedPortefeuille != null) {
            return ResponseEntity.ok(updatedPortefeuille);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortefeuille(@PathVariable Long id) {
        portfeuilleService.deletePortefeuille(id);
        return ResponseEntity.noContent().build();
    }
}