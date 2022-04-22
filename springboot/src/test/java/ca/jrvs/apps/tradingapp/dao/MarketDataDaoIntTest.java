package ca.jrvs.apps.tradingapp.dao;

import ca.jrvs.apps.tradingapp.model.IexQuote;
import ca.jrvs.apps.tradingapp.model.config.MarketDataConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketDataDaoIntTest {

    MarketDataDao dao;
    @BeforeEach
    void setUp() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        Dotenv dotenv = Dotenv.load();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(5);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(dotenv.get("IEX_TOKEN"));

        dao = new MarketDataDao(cm, marketDataConfig);
    }

    @Test
    void findByTicker() {
        String ticker = "AAPL";
        IexQuote quote = dao.findById(ticker).get();
        assertEquals(ticker, quote.getSymbol());
    }

    @Test
    void findAllById() {
        List<IexQuote> quotes = dao.findAllById(List.of("AAPL", "MSFT"));
        assertEquals(2, quotes.size());
        assertEquals("AAPL", quotes.get(0).getSymbol());
        assertEquals("MSFT", quotes.get(1).getSymbol());
    }
}