package tn.esprit.fundsphere.Services.Backtesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnomalyDetectionService {

    private final RestTemplate restTemplate;

    @Autowired
    public AnomalyDetectionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> detectAnomalies(List<Map<String, Object>> transactions) {
        String url = "http://127.0.0.1:5000/detect-anomalies";  // L'URL de l'API Flask

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("transactions", transactions);


        Map<String, Object> response = restTemplate.postForObject(url, requestBody, Map.class);

        return response;
    }
}
