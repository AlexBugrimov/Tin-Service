package dev.bug.tinkoffservice.service;

import dev.bug.tinkoffservice.model.Stock;
import dev.bug.tinkoffservice.model.StockPrice;

import java.util.List;

public interface StockService {

    Stock getStockByTicker(String ticker);

    List<Stock> getStocksByTickers(List<String> tickers);

    List<StockPrice> getPrices(List<String> figies);
}
