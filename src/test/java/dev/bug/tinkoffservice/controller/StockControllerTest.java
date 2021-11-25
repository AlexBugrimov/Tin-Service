package dev.bug.tinkoffservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bug.tinkoffservice.mocks.PriceMock;
import dev.bug.tinkoffservice.mocks.StockMock;
import dev.bug.tinkoffservice.model.Stock;
import dev.bug.tinkoffservice.model.StockPrice;
import dev.bug.tinkoffservice.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StockService stockService;

    @Test
    void getStockWhenFindAnyTickerThenReturnStock() throws Exception {
        Stock stock = StockMock.getStock("Stock #1");
        when(stockService.getStockByTicker(anyString())).thenReturn(stock);

        String result = mockMvc.perform(get("/stocks/FX_TEST"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        verify(stockService, times(1)).getStockByTicker(anyString());
        assertThat(result).isEqualTo(objectMapper.writeValueAsString(stock));
    }

    @Test
    void getStocksWhenListTicketsThenReturnListStocks() throws Exception {
        int stocksCount = 3;
        List<Stock> stocks = StockMock.getStocks(stocksCount);
        when(stockService.getStocksByTickers(anyList())).thenReturn(stocks);

        mockMvc.perform(get("/stocks?tickers=FX,V,MA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(stocksCount)));

        verify(stockService, times(1)).getStocksByTickers(anyList());
    }

    @Test
    void getPricesWhenListFigiesThenReturnListPrices() throws Exception {
        int priceCount = 2;
        List<StockPrice> prices = PriceMock.getPrices(priceCount);
        when(stockService.getPrices(anyList())).thenReturn(prices);

        mockMvc.perform(get("/prices?figies=AZ12,DC96"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(priceCount)));

        verify(stockService, times(1)).getPrices(anyList());
    }
}