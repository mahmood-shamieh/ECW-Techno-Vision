package com.example.momoPlans.model;

//import com.example.momoPlans.model.Balance;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@JacksonXmlRootElement(localName = "getbalanceresponse", namespace = "http://www.ericsson.com/em/emm/financial/v1_2")
//public class GetBalanceResponse {
//
//    @JacksonXmlProperty(localName = "balance")
//    private Balance balance;
//
//    // Getters and Setters
//    public Balance getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Balance balance) {
//        this.balance = balance;
//    }
//}

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"balance", "positivereservations", "negativereservations", "loyalty", "overdraft", "responsecode"})
@XmlRootElement(name = "getbalanceresponse", namespace = "http://www.ericsson.com/em/emm/financial/v1_2")
public class GetBalanceResponse {

    protected MoneyDetailsType balance;
    protected MoneyDetailsType positivereservations;
    protected MoneyDetailsType negativereservations;
    protected Loyalty loyalty;
    protected OverDraftValue overdraft;
    protected String responsecode;
}
