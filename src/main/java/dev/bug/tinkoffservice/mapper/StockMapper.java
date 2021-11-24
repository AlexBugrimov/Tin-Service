package dev.bug.tinkoffservice.mapper;

import dev.bug.tinkoffservice.model.Currency;
import dev.bug.tinkoffservice.model.Stock;
import org.springframework.stereotype.Component;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

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
}
