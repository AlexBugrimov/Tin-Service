package dev.bug.tinkoffservice.mocks;

import dev.bug.tinkoffservice.model.StockPrice;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class PriceMock {

    public static List<StockPrice> getPrices(int count) {
        return IntStream.range(0, count)
                .mapToObj(PriceMock::getPrice)
                .collect(Collectors.toList());
    }

    private static StockPrice getPrice(int idx) {
        return new StockPrice("Figi #" + idx, new Random().nextDouble());
    }
}
