package dev.bug.tinkoffservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class Stock {

    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
}
