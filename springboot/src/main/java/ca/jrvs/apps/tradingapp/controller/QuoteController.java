package ca.jrvs.apps.tradingapp.controller;

import ca.jrvs.apps.tradingapp.model.domain.IexQuote;
import ca.jrvs.apps.tradingapp.model.domain.Quote;
import ca.jrvs.apps.tradingapp.service.QuoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quote")
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @ApiOperation(value = "update a given quote in the database" , notes = "update a given quote in the database")
    @PutMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Quote putQuote(@RequestBody Quote quote) {
        try {
            return quoteService.saveQuote(quote);
        } catch (Exception e) {
            throw new RuntimeException("failed to update quote", e);
        }
    }

    @ApiOperation(value = "add a new ticker to the dailyList" , notes = "add a new ticker to the dailyList")
    @PostMapping("/tickerId/{tickerId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Quote createQuote(@PathVariable String tickerId) {
        try {
            return quoteService.saveQuote(tickerId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create quote", e);
        }
    }


    @ApiOperation(value = "Show iexQuote", notes = "Show iexQuote for a given ticker")
    @GetMapping("/iex/ticker/{ticker}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public IexQuote getQuote(@PathVariable String ticker) {
        try {
            return quoteService.findQuoteByTicker(ticker);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ticker not found", e);
        }
    }

    @ApiOperation(value = "Update quote table using iex data" , notes = "Update quote table using iex data")
    @PutMapping("/iexMarketData")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void updateMarketData() {
        quoteService.updateMarketData();
    }
    @GetMapping("/dailyList")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Quote> getDailyList() {
        try {
            return quoteService.findAllQuotes();
        } catch (Exception e) {
            throw new RuntimeException("Ticker not found", e);
        }
    }
}
