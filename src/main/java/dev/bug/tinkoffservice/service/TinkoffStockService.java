package dev.bug.tinkoffservice.service;

import dev.bug.tinkoffservice.exception.StockNotFoundException;
import dev.bug.tinkoffservice.mapper.StockMapper;
import dev.bug.tinkoffservice.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class TinkoffStockService implements StockService {

    private final OpenApi openApi;
    private final StockMapper stockMapper;

    @Async
    public CompletableFuture<MarketInstrumentList> getMarketInstrumentTicker(String ticker) {
        MarketContext marketContext = openApi.getMarketContext();
        return marketContext.searchMarketInstrumentsByTicker(ticker);
    }

    @Override
    public Stock getStockByTicker(String ticker) {
        CompletableFuture<MarketInstrumentList> marketInstrumentTicker = getMarketInstrumentTicker(ticker);
        List<MarketInstrument> instruments = marketInstrumentTicker.join().getInstruments();

        if (instruments.isEmpty()) {
            throw new StockNotFoundException(String.format("Stock %s not found", ticker));
        }
        return stockMapper.toStock(instruments.get(0));
    }

    @Override
    public List<Stock> getStocksByTickers(List<String> tickers) {
        List<CompletableFuture<MarketInstrumentList>> marketInstrument = tickers.stream()
                .map(this::getMarketInstrumentTicker)
                .collect(Collectors.toList());
        return marketInstrument.stream()
                .map(CompletableFuture::join)
                .filter(not(mi -> mi.getInstruments().isEmpty()))
                .map(mi -> mi.getInstruments().get(0))
                .map(stockMapper::toStock)
                .collect(Collectors.toList());
    }
}
