package com.example.momoPlans.controller;


import com.example.momoPlans.service.BalanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/getbalance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @PostMapping("")
    public ResponseEntity callSoapService(@RequestBody Map test) {
        try {
            return ResponseEntity.ok(
                    balanceService.getBalanceResponse(
                            Optional.ofNullable(String.valueOf(test.get("fri"))),
                            Optional.ofNullable(Boolean.valueOf(test.get("includeReservation").toString())),
                            Optional.ofNullable(String.valueOf(test.get("quoteId")))
                    )
            );
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e);
        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e);
        } catch (ResourceAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (RestClientException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PostMapping("/dummy/{id}")
    public ResponseEntity callDummyApi(@PathVariable int id) {
        return ResponseEntity.ok(balanceService.getDummyApiResponse(id));

    }
//    this is from the test branch
}

