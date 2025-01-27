package tn.esprit.fundsphere.Services.OutilService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TradingCoachService {

    private final WebClient webClient;

    // Fetch stock price
    public Mono<String> getStockPrice(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/get_stock_price")
                        .queryParam("symbol", symbol)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    // Fetch market summary
    public Mono<String> getMarketSummary() {
        return webClient.get()
                .uri("/get_market_summary")
                .retrieve()
                .bodyToMono(String.class);
    }

    // Ask a question to the bot
    public Mono<String> askQuestion(String question) {
        return webClient.post()
                .uri("/ask_question")
                .bodyValue(Map.of("question", question))
                .retrieve()
                .bodyToMono(String.class);
    }
}


