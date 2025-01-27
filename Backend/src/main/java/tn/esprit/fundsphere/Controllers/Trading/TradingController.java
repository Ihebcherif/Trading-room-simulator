package tn.esprit.fundsphere.Controllers.Trading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundsphere.Services.Backtesting.AnomalyDetectionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trading")
public class TradingController {

    private final AnomalyDetectionService anomalyDetectionService;

    @Autowired
    public TradingController(AnomalyDetectionService anomalyDetectionService) {
        this.anomalyDetectionService = anomalyDetectionService;
    }

    @PostMapping("/detect-anomalies")
    public ResponseEntity<Map<String, Object>> detectAnomalies(@RequestBody List<Map<String, Object>> transactions) {
        Map<String, Object> anomalies = anomalyDetectionService.detectAnomalies(transactions);
        return ResponseEntity.ok(anomalies);
    }
}
