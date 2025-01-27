package tn.esprit.fundsphere.Services.TransactionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoExchangeService {

    // URL de l'API avec votre clé d'API
    private static final String API_URL = "https://api.coinlayer.com/api/live?access_key=aa7b6292ffc3e60b4b7a8fa427c89b4b=BTC,TND";

    private final RestTemplate restTemplate;

    // Constructeur du service avec RestTemplate
    public CryptoExchangeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Méthode pour récupérer le taux de change Bitcoin -> Dinar Tunisien
    public double getBtcToDinarRate() {
        try {
            // Requête GET vers l'API CoinLayer
            ResponseEntity<CryptoRateResponse> responseEntity = restTemplate.getForEntity(API_URL, CryptoRateResponse.class);

            // Vérification du code de statut de la réponse
            if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
                CryptoRateResponse response = responseEntity.getBody();
                if (response != null && response.getRates() != null) {
                    // Retourner le taux BTC -> TND
                    return response.getRates().getTND();
                }
            }
            throw new IllegalStateException("Taux de conversion BTC/TND non disponible ou réponse de l'API invalide.");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du taux de change crypto : " + e.getMessage(), e);
        }
    }

    // Classe interne pour mapper la réponse JSON de l'API
    public static class CryptoRateResponse {
        private String success;
        private String timestamp;
        private Rates rates;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public Rates getRates() {
            return rates;
        }

        public void setRates(Rates rates) {
            this.rates = rates;
        }

        // Classe pour les taux de conversion
        public static class Rates {
            private double BTC;
            private double TND;

            public double getBTC() {
                return BTC;
            }

            public void setBTC(double BTC) {
                this.BTC = BTC;
            }

            public double getTND() {
                return TND;
            }

            public void setTND(double TND) {
                this.TND = TND;
            }
        }
    }
}
