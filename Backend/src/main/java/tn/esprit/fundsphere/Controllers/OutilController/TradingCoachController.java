package tn.esprit.fundsphere.Controllers.OutilController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tn.esprit.fundsphere.Services.OutilService.TradingCoachService;

import java.util.Map;

@RestController
@RequestMapping("/api/trading-coach")
@RequiredArgsConstructor
public class TradingCoachController {

    private final TradingCoachService tradingCoachService;

    // Get stock price by symbol
    @GetMapping("/stock-price")
    public Mono<String> getStockPrice(@RequestParam String symbol) {
        return tradingCoachService.getStockPrice(symbol);
    }

    // Get market summary
    @GetMapping("/market-summary")
    public Mono<String> getMarketSummary() {
        return tradingCoachService.getMarketSummary();
    }

    // Ask a question to the bot
    @PostMapping("/ask-question")
    public Mono<String> askQuestion(@RequestBody Map<String, String> body) {
        String question = body.get("question");
        return tradingCoachService.askQuestion(question);
    }
}


