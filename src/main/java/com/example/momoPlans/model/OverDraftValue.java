package com.example.momoPlans.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overdraftvalue", propOrder = {"overdraft", "overdraftlimit", "overdraftaccessfee", "overdraftinterest"})

public class OverDraftValue {

    @XmlElement(required = true)
    protected MoneyDetailsType overdraft;
    @XmlElement(required = true)
    protected MoneyDetailsType overdraftlimit;
    @XmlElement(required = true)
    protected MoneyDetailsType overdraftaccessfee;
    @XmlElement(required = true)
    protected MoneyDetailsType overdraftinterest;
}

