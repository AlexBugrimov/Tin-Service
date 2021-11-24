package dev.bug.tinkoffservice.model;

import lombok.Data;

@Data
public class Stock {

    private String ticker;
    private String figi;
    private String name;
    private String type;
    private Currency currency;
    private String source;
}
