package tn.esprit.fundsphere.Scraping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class AlphaVantageService {

    private static final String API_KEY = "XGVCBMAB3DFBKUNS";
    private static final String BASE_URL = "https://www.alphavantage.co/query?";

    @Autowired
    private StockDataRepository stockDataRepository;

    // List of stock symbols to fetch data for
    private List<String> stockSymbols = Arrays.asList("AAPL", "GOOGL", "AMZN", "MSFT");

    // Fetch data for all stocks
    public void fetchAndSaveStockData() throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        for (String symbol : stockSymbols) {
            String url = BASE_URL + "function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + API_KEY;
            String response = restTemplate.getForObject(url, String.class);

            // Parse the response and extract the stock data
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode timeSeries = rootNode.path("Time Series (Daily)");
            String latestDate = timeSeries.fieldNames().next();  // Get the most recent date
            JsonNode latestData = timeSeries.path(latestDate);
            String closePrice = latestData.path("4. close").asText();
// Sleep for 12 seconds to avoid hitting rate limit (for the free tier)
            Thread.sleep(12000);
            // Save the stock data to the database
            StockData stockData = new StockData();
            stockData.setSymbol(symbol);
            stockData.setPrice(closePrice);
            stockData.setTimestamp(LocalDateTime.now());

            stockDataRepository.save(stockData);
        }
    }
}
