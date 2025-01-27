package tn.esprit.fundsphere.Services.Backtesting;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BacktestService { //

    private final String BASE_URL = "http://localhost:5000"; // Change le port si nécessaire
    private final RestTemplate restTemplate;

    // Injection de RestTemplate par le constructeur
    public BacktestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String runBacktest() {
        String url = BASE_URL + "/backtest";
        return restTemplate.postForObject(url, null, String.class);
    }
}
