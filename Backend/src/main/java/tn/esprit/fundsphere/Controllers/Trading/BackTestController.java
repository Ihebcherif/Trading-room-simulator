package tn.esprit.fundsphere.Controllers.Trading;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundsphere.Services.Backtesting.BacktestService;

@RestController
public class BackTestController {

    private final BacktestService backtestService;

    public BackTestController(BacktestService backtestService) {
        this.backtestService = backtestService;
    }

    @GetMapping("/api/backtest")
    public ResponseEntity<String> triggerBacktest() {
        try {
            String result = backtestService.runBacktest();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'exécution du backtest : " + e.getMessage());
        }
    }
}

