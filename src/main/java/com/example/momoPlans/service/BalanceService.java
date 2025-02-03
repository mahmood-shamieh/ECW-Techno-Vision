package com.example.momoPlans.service;


import com.example.momoPlans.model.GetBalanceResponse;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.Optional;

@Service
public class BalanceService {
    @Value("${soap.api.endpoint}")
    private String ENDPOINT;
    @Value("${soap.api.action}")
    private String Action;
    @Value("${soap.api.auth}")
    private String AUTH_HEADER;

//    @Autowired
//    AuthConfig authConfig;

    public String callBalanceEndPoint(String requestXml) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("cookie", AUTH_HEADER);
        headers.add("SOAPAction", Action);
        HttpEntity<String> request = new HttpEntity<>(requestXml, headers);
        return restTemplate.postForObject(ENDPOINT, request, String.class);

    }

    public String callDummyApi(String requestXml) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", "NumberToWords");
        HttpEntity<String> request = new HttpEntity<>(requestXml, headers);
        return restTemplate.postForObject("https://www.dataaccess.com/webservicesserver/NumberConversion.wso", request, String.class);

    }

    public String getDummyApiResponse(int number) {
        String request = """
                <?xml version="1.0" encoding="utf-8"?>
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                  <soap:Body>
                    <NumberToWords xmlns="http://www.dataaccess.com/webservicesserver/">
                      <ubiNum>
                      """ + number + """
                    </ubiNum>
                    </NumberToWords>
                  </soap:Body>
                </soap:Envelope>
                """;
        return callDummyApi(request);

    }

    public Object getBalanceResponse(Optional<String> fri, Optional<Boolean> includeReservations, Optional<String> quoteid) throws Exception {
        StringBuilder stringRequest = new StringBuilder("""
                <?xml version="1.0" encoding="UTF-8"?><ns2:getbalancerequest xmlns:ns2="http://www.ericsson.com/em/emm/financial/v1_2">
                """);
        if (!fri.isEmpty()) {
            stringRequest.append("<fri>" + fri.get().toString() + "</fri>");
        }
        if (!includeReservations.isEmpty()) {
            stringRequest.append("<includereservations>" + includeReservations.get().toString() + "</includereservations>");
        }
        if (!quoteid.isEmpty()) {
            stringRequest.append("<quoteid>" + quoteid.get().toString() + "</quoteid>");
        }
        stringRequest.append("</ns2:getbalancerequest>");
        String response = """
                <?xml version="1.0" encoding="UTF-8"?>
                    <ns5:getbalanceresponse xmlns:ns5="http://www.ericsson.com/em/emm/financial/v1_2" xmlns:fic="http://www.ericsson.com/em/emm/financial/v1_0/common" xmlns:op="http://www.ericsson.com/em/emm/v1_0/common" xmlns:xs="http://www.w3.org/2001/XMLSchema">
                                   <balance>
                                      <amount>8999</amount>
                                      <currency>XAF</currency>
                                   </balance>
                    </ns5:getbalanceresponse>
                """;
//        String response = callBalanceEndPoint(stringRequest.toString());
        XmlMapper mapper = new XmlMapper();
        GetBalanceResponse getBalanceResponse = null;
        try {
            getBalanceResponse = mapper.readValue(response, GetBalanceResponse.class);
        } catch (Exception e) {
            throw e;
        }
        return getBalanceResponse;
    }
}

