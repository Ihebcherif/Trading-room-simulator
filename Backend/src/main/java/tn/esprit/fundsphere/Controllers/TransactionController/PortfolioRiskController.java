package tn.esprit.fundsphere.Controllers.TransactionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundsphere.Entities.TransactionManagement.Ordre;
import tn.esprit.fundsphere.Repositories.TransactionRepository.OrdreRepository;
import tn.esprit.fundsphere.Services.TransactionService.IPortfolioRiskAnalysisService;

import java.util.List;

@RestController
@RequestMapping("/portfoliorisk")
public class PortfolioRiskController {

    private final IPortfolioRiskAnalysisService portfolioRiskAnalysisService;
    private final OrdreRepository ordreRepository;

    @Autowired
    public PortfolioRiskController(IPortfolioRiskAnalysisService portfolioRiskAnalysisService, OrdreRepository ordreRepository) {
        this.portfolioRiskAnalysisService = portfolioRiskAnalysisService;
        this.ordreRepository = ordreRepository;
    }

    @GetMapping("/portfolio-var/{idPortefeuille}/{confidence}")
    public ResponseEntity<Double> getPortfolioVaR(@PathVariable("idPortefeuille") int idPortefeuille, @PathVariable("confidence") double confidenceLevel) {
        try {
            List<Ordre> orders = ordreRepository.findByPortefeuille_IdPortefeuille(idPortefeuille);
            double var = portfolioRiskAnalysisService.calculatePortfolioVaR(orders, confidenceLevel);
            return ResponseEntity.ok(var);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}