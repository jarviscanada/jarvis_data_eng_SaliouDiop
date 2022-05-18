package ca.jrvs.apps.tradingapp.controller;

import ca.jrvs.apps.tradingapp.model.domain.Account;
import ca.jrvs.apps.tradingapp.model.domain.Trader;
import ca.jrvs.apps.tradingapp.model.domain.TraderAccountView;
import ca.jrvs.apps.tradingapp.service.TraderAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Api(value = "Trader", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("api/v1/trader")
public class TraderAccountController {

    private TraderAccountService traderAccountService;

    @Autowired
    public TraderAccountController(TraderAccountService traderAccountService) {
        this.traderAccountService = traderAccountService;
    }

    @ApiOperation(value = "Create a trader and account", notes = "Create a trader and account")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(
            path = "/firstname/{firstName}/lastname/{lastName}/dob/{dob}/country/{country}/email/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TraderAccountView createTraderAccount(
            @PathVariable String firstname,
            @PathVariable String lastname,
            @PathVariable String dob,
            @PathVariable String country,
            @PathVariable String email) {
        try {
            Trader trader = new Trader();
            trader.setFirstName(firstname);
            trader.setLastName(lastname);
            trader.setDob(Date.valueOf(dob));
            trader.setCountry(country);
            trader.setEmail(email);
            return traderAccountService.createTraderAndAccount(trader);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @ApiOperation(value = "Create a trader and account with DTO", notes = "Create a trader and account with DTO")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public TraderAccountView createTraderAccount(@RequestBody Trader trader) {
        try {
            return traderAccountService.createTraderAndAccount(trader);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @ApiOperation(value = "Get a trader account by id", notes = "Get a trader account by id")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TraderAccountView getTraderAccountById(@PathVariable Integer id) {
        try {
            return traderAccountService.getTraderAccountById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @ApiOperation(value = "Delete a trader by id", notes = "Delete a trader by id")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTraderAccount(@PathVariable Integer traderId) {
        try {
            traderAccountService.deleteTraderById(traderId);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @ApiOperation(value = "Deposit funds to an account", notes = "Deposit funds to an account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful deposit"),
            @ApiResponse(code = 400, message = "Invalid deposit amount"),
            @ApiResponse(code = 404, message = "Trader account not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping(path = "/{id}/deposit/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account deposit(@PathVariable Integer id, @PathVariable Double amount) {
        try {
            return traderAccountService.deposit(id, amount);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @ApiOperation(value = "Withdraw funds from an account", notes = "Withdraw funds from an account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful withdrawal"),
            @ApiResponse(code = 400, message = "Invalid withdrawal amount"),
            @ApiResponse(code = 404, message = "Trader account not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping(path = "/{id}/withdraw/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account withdraw(@PathVariable Integer id, @PathVariable Double amount) {
        try {
            return traderAccountService.withdraw(id, amount);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}