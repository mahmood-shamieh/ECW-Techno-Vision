package com.example.momoPlans.model;

import com.example.momoPlans.model.Balance;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "getbalanceresponse", namespace = "http://www.ericsson.com/em/emm/financial/v1_2")
public class GetBalanceResponse {

    @JacksonXmlProperty(localName = "balance")
    private Balance balance;

    // Getters and Setters
    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}


