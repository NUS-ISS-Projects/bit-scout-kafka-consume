package com.example.coin.controller;

import com.example.coin.service.BinanceService;
import com.example.coin.consumer.BinanceWebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CryptoController {

    @Autowired
    private BinanceService binanceService;

    private final BinanceWebSocketClient binanceWebSocketClient;

    public CryptoController(BinanceWebSocketClient binanceWebSocketClient) {
        this.binanceWebSocketClient = binanceWebSocketClient;
    }

    @PostMapping("/subscribe")
    public String subscribeCoins(@RequestBody List<String> coinPairs) {
        binanceWebSocketClient.subscribeToCoins(coinPairs);
        return "Subscribed to coins: " + coinPairs;
    }

    @PostMapping("/unsubscribe")
    public String unsubscribeCoins(@RequestBody List<String> coinPairs) {
        binanceWebSocketClient.unsubscribeFromCoins(coinPairs);
        return "Unsubscribed from coins: " + coinPairs;
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchCoin(@RequestParam String symbol) {
        return binanceService.searchCoin(symbol)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
