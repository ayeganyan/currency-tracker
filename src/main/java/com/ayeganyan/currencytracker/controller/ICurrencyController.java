package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.model.CurrencyRate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/currency", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ICurrencyController {

    @ApiOperation(
            value = "Returns latest conversion rate",
            response = CurrencyRate.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid currency codes are specified"),
            @ApiResponse(code = 404, message = "Exchange rate not found")
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/from/{fromCurrency}/to/{toCurrency}/latest")
    ResponseEntity<CurrencyRate> getLatestCurrencyRate(
            @ApiParam(value = "Currency code from which to convert", required = true)
            @PathVariable String fromCurrency,

            @ApiParam(value = "Currency code to convert", required = true)
            @PathVariable String toCurrency
    );

    @ApiOperation(
            value = "Returns conversion rates for specified time range",
            response = CurrencyRate.class,
            responseContainer = "list",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid currency codes are specified"),
            @ApiResponse(code = 404, message = "Exchange rate not found")
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/from/{fromCurrency}/to/{toCurrency}/range")
    ResponseEntity<List<CurrencyRate>> getCurrencyRateForRange(
            @ApiParam(value = "Currency code from which to convert", required = true)
            @PathVariable String fromCurrency,

            @ApiParam(value = "Currency code to convert", required = true)
            @PathVariable String toCurrency,

            @Valid
            @ApiParam(value = "Start date of range (use ISO datetime format, e.g. 2000-10-31T01:30:00.000-05:00", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @PastOrPresent
            @RequestParam Date fromDate,

            @Valid
            @ApiParam(value = "End date of range, now if not specified (use ISO datetime format, e.g. 2000-10-31T01:30:00.000-05:00")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @PastOrPresent
            @RequestParam(required = false) Date toDate
    );

    @ApiOperation(
            value = "Adds conversion rate manually",
            response = CurrencyRate.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid currency code is specified"),
            @ApiResponse(code = 201, message = "New data point has been successfully added")
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/from/{from}/to/{to}")
    ResponseEntity<CurrencyRate> addCurrencyRate(
            @ApiParam(value = "Currency code from which to convert", required = true)
            @PathVariable String from,

            @ApiParam(value = "Currency code to convert", required = true)
            @PathVariable String to,

            @ApiParam(value = "Exchange rate to add", required = true)
            @RequestParam Double rate
    );
}
