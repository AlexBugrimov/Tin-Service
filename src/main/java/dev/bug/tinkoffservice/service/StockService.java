package dev.bug.tinkoffservice.service;

import dev.bug.tinkoffservice.model.Stock;

public interface StockService {

    Stock getStockByTicker(String ticker);
}
