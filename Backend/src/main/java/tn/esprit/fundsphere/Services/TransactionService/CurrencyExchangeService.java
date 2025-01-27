package tn.esprit.fundsphere.Services.TransactionService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyExchangeService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/TND";
    private final RestTemplate restTemplate;

    public CurrencyExchangeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getEuroToDinarRate() {
        try {
            CurrencyRateResponse response = restTemplate.getForObject(API_URL, CurrencyRateResponse.class);
            if (response != null && response.getRates().containsKey("EUR")) {
                return response.getRates().get("EUR");
            }
            throw new IllegalStateException("Taux de conversion EUR/TND non disponible");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du taux de change : " + e.getMessage(), e);
        }
    }

    // Internal class to map API response
    public static class CurrencyRateResponse {
        private Map<String, Double> rates;

        public Map<String, Double> getRates() {
            return rates;
        }

        public void setRates(Map<String, Double> rates) {
            this.rates = rates;
        }
    }
}
