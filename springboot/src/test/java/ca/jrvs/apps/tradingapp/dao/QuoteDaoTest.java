package ca.jrvs.apps.tradingapp.dao;

import ca.jrvs.apps.tradingapp.TestConfig;
import ca.jrvs.apps.tradingapp.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
class QuoteDaoTest {
    @Autowired
    private QuoteDao quoteDao;
    private Quote quote;

    @Before
    public void insertOneQuote() {
        quote = new Quote();
        quote.setAskPrice(100d);
        quote.setAskSize(10);
        quote.setBidPrice(90.0);
        quote.setLastPrice(95.0);
        quote.setBidSize(20);
        quote.setId("aapl");
        quoteDao.save(quote);
    }

    @After
    public void deleteOneQuote() {
        quoteDao.deleteById(quote.getId());
    }

    @Test
    void save() {
        quote.setAskPrice(100d);
        quote.setAskSize(10);
        quote.setBidPrice(90.0);
        quote.setLastPrice(95.0);
        quote.setBidSize(20);
        quote.setId("aapl");
        quoteDao.save(quote);
        assertEquals(quote, quoteDao.findById(quote.getId()));
    }

    @Test
    void saveAll() {
    }

    @Test
    void findById() {
        assertEquals(quote, quoteDao.findById(quote.getId()));
    }

    @Test
    void existsById() {
        assertTrue(quoteDao.existsById(quote.getId()));
    }

    @Test
    void count() {
        assertEquals(1, quoteDao.count());
    }

    @Test
    void deleteById() {
        quoteDao.deleteById(quote.getId());
        assertNull(quoteDao.findById(quote.getId()));
    }
}