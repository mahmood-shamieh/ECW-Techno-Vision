package com.example.momoPlans.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loyalty", propOrder = {"generated", "consumed", "newbalance"})

public class Loyalty {
    protected BigDecimal generated;
    protected BigDecimal consumed;
    protected BigDecimal newbalance;


}
