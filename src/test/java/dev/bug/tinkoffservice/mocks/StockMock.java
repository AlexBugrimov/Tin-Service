package dev.bug.tinkoffservice.mocks;

import dev.bug.tinkoffservice.model.Currency;
import dev.bug.tinkoffservice.model.Stock;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public final class StockMock {

    public static Stock getStock(String name) {
        return Stock.builder()
                .name(name)
                .source("TINKOFF")
                .type("type")
                .currency(Currency.EUR)
                .figi("ASC123")
                .build();
    }

    public static List<Stock> getStocks(int count) {
        return IntStream.range(0, count)
                .mapToObj(idx -> getStock(String.valueOf(idx)))
                .collect(Collectors.toList());
    }
}
