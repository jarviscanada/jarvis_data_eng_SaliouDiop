package ca.jrvs.apps.tradingapp.service;

import ca.jrvs.apps.tradingapp.TestConfig;
import ca.jrvs.apps.tradingapp.dao.QuoteDao;
import ca.jrvs.apps.tradingapp.model.domain.IexQuote;
import ca.jrvs.apps.tradingapp.model.domain.Quote;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
class QuoteServiceTest {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private QuoteDao quoteDao;
    private Quote quote;

    @Before
    public void setup() {
        quoteDao.deleteAll();
    }

    @Test
    void updateMarketData() {
        quote = new Quote();
        quote.setTicker("AAPL");
        quote.setAskPrice(100.0);
        quote.setBidPrice(100.0);
        quote.setLastPrice(100.0);
        quote.setBidSize(100);
        quote.setAskSize(100);

        quoteService.saveQuote(quote);
        quoteService.updateMarketData();
        List<Quote> quotes = quoteService.findAllQuotes();
        assertEquals(quotes.get(0).getAskPrice(), 100.0);
    }


    @Test
    void saveQuotes() {
        List<String> tickers = List.of("AAPL", "MSFT", "GOOG");
        List<Quote> quotes = quoteService.saveQuote(tickers);
        assertEquals(quotes.size(), 3);
    }

    @Test
    void saveQuote() {
        Quote quote = quoteService.saveQuote("AAPL");
        assertEquals(quote.getTicker(), "AAPL");
    }

    @Test
    void findQuoteByTicker() {
        assertEquals(quoteService.findQuoteByTicker("AAPL"), quoteDao.findById("AAPL"));
    }

    @Test
    void findAllQuotes() {
        List<Quote> quotes = quoteService.findAllQuotes();
        assertEquals(quotes.size(), 0);
    }
}