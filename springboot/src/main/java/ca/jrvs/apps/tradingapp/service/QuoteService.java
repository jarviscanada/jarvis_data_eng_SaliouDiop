package ca.jrvs.apps.tradingapp.service;

import ca.jrvs.apps.tradingapp.dao.MarketDataDao;
import ca.jrvs.apps.tradingapp.dao.QuoteDao;
import ca.jrvs.apps.tradingapp.model.domain.IexQuote;
import ca.jrvs.apps.tradingapp.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Update quote table against IEX source
     * get all quotes from the db
     * foreach ticker get the quote from IEX
     * convert iexQuote to quote entity
     * persist quote to db
     * @throws org.springframework.dao.DataAccessException if there is an error accessing the database
     * @throws IllegalArgumentException for invalid ticker
     */

    public void updateMarketData() {
        logger.info("Updating market data");
        List<Quote> quotes = findAllQuotes();
        for (Quote quote : quotes) {
            IexQuote iexQuote = marketDataDao.findById(quote.getTicker()).orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));
            Quote quoteFromIex = buildQuoteFromIexQuote(iexQuote);
            quoteDao.save(quoteFromIex);
        }

    }

    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
        Quote quote = new Quote();
        quote.setTicker(iexQuote.getSymbol());
        quote.setAskPrice(iexQuote.getIexAskPrice());
        quote.setAskSize(iexQuote.getIexAskSize());
        quote.setBidPrice(iexQuote.getIexBidPrice());
        quote.setBidSize(iexQuote.getIexBidSize());
        quote.setLastPrice(iexQuote.getLatestPrice());

        return quote;
    }

    public List<Quote> saveQuote(List<String> tickers) {
        List<Quote> quotes = new ArrayList<>();
        for (String ticker : tickers) {
            quotes.add(saveQuote(ticker));
        }
        return quotes;
    }
    public Quote saveQuote(String ticker) {
        IexQuote iexQuote = marketDataDao
                .findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));
        Quote quote = buildQuoteFromIexQuote(iexQuote);
        return quoteDao.save(quote);
    }
    public IexQuote findQuoteByTicker(String ticker) {
        return marketDataDao
                .findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));
    }
    public Quote saveQuote(Quote quote) {
        return quoteDao.save(quote);
    }

    public List<Quote> findAllQuotes() {
        return quoteDao.findAll();
    }

}
