package tn.esprit.fundsphere.Scraping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockDataScheduler {

    @Autowired
    private AlphaVantageService alphaVantageService;

    @Scheduled(fixedRate = 86400000) // Every 24 hours
    public void scheduleStockDataFetch() throws Exception {
        alphaVantageService.fetchAndSaveStockData(); // Fetch Apple stock data
    }
}

