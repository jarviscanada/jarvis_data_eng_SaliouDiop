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

    @ApiOperation(value = "Show iexQuote", notes = "Show iexQuote for a given ticker")
//    @ApiResponses(value = {
//            ApiResponse(code = 200, message = "Successfully retrieved quote"),
//            ApiResponse(code = 404, message = "Ticker not found")
//    })
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
