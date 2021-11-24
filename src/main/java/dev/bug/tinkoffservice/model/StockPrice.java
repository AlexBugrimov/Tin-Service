package dev.bug.tinkoffservice.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class StockPrice {

    String figi;
    Double price;
}
