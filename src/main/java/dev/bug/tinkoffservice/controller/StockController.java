package dev.bug.tinkoffservice.controller;

import dev.bug.tinkoffservice.model.Stock;
import dev.bug.tinkoffservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stocks/{ticker}")
    public ResponseEntity<Stock> getStock(@PathVariable String ticker) {
        return ResponseEntity.ok(stockService.getStockByTicker(ticker));
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getStocks(@RequestParam List<String> tickers) {
        return ResponseEntity.ok(stockService.getStocksByTickers(tickers));
    }
}
