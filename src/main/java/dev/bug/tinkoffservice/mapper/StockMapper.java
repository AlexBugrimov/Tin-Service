package dev.bug.tinkoffservice.mapper;

import dev.bug.tinkoffservice.model.Currency;
import dev.bug.tinkoffservice.model.Stock;
import dev.bug.tinkoffservice.model.StockPrice;
import org.springframework.stereotype.Component;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

@Component
public class StockMapper {

    private static final String TINKOFF_SOURCE = "TINKOFF";

    public Stock toStock(MarketInstrument instrument) {
        return Stock.builder()
                .name(instrument.getName())
                .ticker(instrument.getTicker())
                .figi(instrument.getFigi())
                .type(instrument.getType().getValue())
                .currency(Currency.valueOf(instrument.getCurrency().getValue()))
                .source(TINKOFF_SOURCE)
                .build();
    }

    public StockPrice toStockPrice(Orderbook orderbook) {
        return new StockPrice(orderbook.getFigi(), orderbook.getLastPrice().doubleValue());
    }
}
