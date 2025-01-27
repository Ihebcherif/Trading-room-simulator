package tn.esprit.fundsphere.Controllers.AssuranceController;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.AssuranceManagement.BacktestResult;
import tn.esprit.fundsphere.Entities.AssuranceManagement.Contract;
import tn.esprit.fundsphere.Entities.TransactionManagement.Ordre;
import tn.esprit.fundsphere.Entities.TransactionManagement.Portefeuille;
import tn.esprit.fundsphere.Repositories.TransactionRepository.OrdreRepository;
import tn.esprit.fundsphere.Services.AssuranceService.AssuranceServiceImpl;
import tn.esprit.fundsphere.Services.AssuranceService.BacktestingService;
import tn.esprit.fundsphere.Services.AssuranceService.IBacktestingService;
import tn.esprit.fundsphere.Services.TransactionService.IPortfolioRiskAnalysisService;


import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/contract")
public class ContractRestController {

    public AssuranceServiceImpl contractService ;
    private final IBacktestingService backtestingService;

    @PostMapping(path = "/add-credit")
    public Contract addContract (@RequestBody Contract credit)
    {
        return contractService.addContract(credit);
    }
    @GetMapping("/show-credits")
    public List<Contract> getAllCredits() {
        List<Contract> listCredits = contractService.getAllContracts();
        return listCredits;
    }

    @DeleteMapping(path = "/delete-credit/{id}")
    public void deleteContract (@PathVariable ("id") Long idCredit)
    {
        contractService.deleteContract(idCredit);
    }


    @PutMapping(path = "/update-contract/{idContract}")
    public Contract updateContract(@PathVariable("idContract") int idContract,@RequestBody Contract cr)
    {

        Contract credit = contractService.updateContract(cr);
        return credit ;
    }
    @GetMapping("/get-contract/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Long id) {
        try {
            // Appel du service pour obtenir le contrat par son ID
            Contract contract = contractService.getContract(id);
            return ResponseEntity.ok(contract);  // Retourner le contrat trouvé avec un statut HTTP 200
        } catch (RuntimeException e) {
            // Si le contrat n'est pas trouvé, retourner un statut 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/calculate-prime/{idPortefeuille}/{confidence}")
    public double calculatePrime(@PathVariable("idPortefeuille") int idPortefeuille,
                                 @PathVariable("confidence") double confidenceLevel) {
        return contractService.calculateContractPrime(idPortefeuille, confidenceLevel);
    }


    @GetMapping("/strategy/{idPortefeuille}")
    public ResponseEntity<BacktestResult> backtestStrategy(@PathVariable("idPortefeuille") Long idPortefeuille) {
        try {
            BacktestResult result = backtestingService.backtestStrategy(idPortefeuille);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
