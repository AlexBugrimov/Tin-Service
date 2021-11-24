package dev.bug.tinkoffservice.service;

import dev.bug.tinkoffservice.model.Stock;

import java.util.List;

public interface StockService {

    Stock getStockByTicker(String ticker);

    List<Stock> getStocksByTickers(List<String> tickers);
}
