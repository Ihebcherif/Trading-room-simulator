package tn.esprit.fundsphere.Controllers.AssuranceController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.AssuranceManagement.CashFlow;
import tn.esprit.fundsphere.Services.AssuranceService.IActuarialCalculator;

import java.util.List;

@RestController
@RequestMapping("/actuarial")
@AllArgsConstructor
public class ActuarialController {

    private final IActuarialCalculator actuarialCalculator;

    /**
     * API pour calculer la VAN ajustée au risque.
     *
     * @param cashFlows Liste des flux de trésorerie
     * @param discountRate Taux d'actualisation
     * @return La valeur actuelle nette ajustée au risque
     */
    @PostMapping("/calculate-npv")
    public ResponseEntity<Double> calculateRiskAdjustedNPV(
            @RequestBody List<CashFlow> cashFlows,
            @RequestParam double discountRate) {

        double npv = actuarialCalculator.calculateRiskAdjustedNPV(cashFlows, discountRate);
        return new ResponseEntity<>(npv, HttpStatus.OK);
    }
}
