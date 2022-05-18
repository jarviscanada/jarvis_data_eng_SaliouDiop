package ca.jrvs.apps.tradingapp.dao;

import ca.jrvs.apps.tradingapp.model.domain.IexQuote;
import ca.jrvs.apps.tradingapp.model.config.MarketDataConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private HttpClientConnectionManager connectionManager;
    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);

    @Autowired
    public MarketDataDao(HttpClientConnectionManager connectionManager, MarketDataConfig marketDataConfig) {
        this.connectionManager = connectionManager;
        this.IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    @Override
    public <S extends IexQuote> S save(S entity) {
        return null;
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote = Optional.empty();
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));
        if (quotes.size() == 0) {
            return iexQuote;
        }
        return Optional.of(quotes.get(0));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }

    /**
     * Get quotes from IEX
     * @param tickers is a list of tickers
     * @return a list of IexQuote objects
     * @throws IllegalArgumentException if tickers is null or empty
     * @throws org.springframework.dao.DataRetrievalFailureException if HTTP request fails
     */

    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {
        List<IexQuote> quotes = new ArrayList<>();
        if (tickers == null || tickers.toString().isEmpty()) {
            throw new IllegalArgumentException("tickers cannot be null or empty");
        }
        for(String ticker : tickers) {
            String url = String.format(IEX_BATCH_URL, ticker);
            Optional<String> response = Optional.ofNullable(
                    executeHttpGet(url).orElseThrow((
                            () -> new DataRetrievalFailureException("HTTP request failed")
                    ))
            );

            ObjectMapper mapper = new ObjectMapper();
            try {
                IexQuote quote = mapper.readValue(response.get(), IexQuote.class);
                quotes.add(quote);
            } catch (IOException e) {
                logger.error("Error parsing IEX quote", e);
                throw new IllegalArgumentException("Error parsing IEX quote");
            }
        }
        return quotes;
    }

    private Optional<String> executeHttpGet(String url) {

        CloseableHttpClient httpClient = createHttpClient();
        HttpUriRequest request = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(request);
            String entity = EntityUtils.toString(response.getEntity());
            return Optional.of(entity);
        } catch (IOException e) {
            logger.error("Error in executing HTTP GET request", e);
            throw new DataRetrievalFailureException("Error in executing HTTP GET request", e);
        }
    }
    private CloseableHttpClient createHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setConnectionManagerShared(true)
                .build();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(IexQuote entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
