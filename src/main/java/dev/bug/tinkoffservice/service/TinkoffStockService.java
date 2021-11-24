package dev.bug.tinkoffservice.service;

import dev.bug.tinkoffservice.exception.StockNotFoundException;
import dev.bug.tinkoffservice.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TinkoffStockService implements StockService {

    private final OpenApi openApi;

    @Override
    public Stock getStockByTicker(String ticker) {
        MarketContext marketContext = openApi.getMarketContext();
        CompletableFuture<MarketInstrumentList> instrumentsCF = marketContext.searchMarketInstrumentsByTicker(ticker);
        List<MarketInstrument> instruments = instrumentsCF.join().getInstruments();

        if (instruments.isEmpty()) {
            throw new StockNotFoundException(String.format("Stock %s not found", ticker));
        }
        return null;
    }
}
