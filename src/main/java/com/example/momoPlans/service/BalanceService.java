package com.example.momoPlans.service;


import com.example.momoPlans.config.Config;
import com.example.momoPlans.model.GetBalanceResponse;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.io.StringReader;
import java.util.Optional;

@Service
public class BalanceService {

    final Config config;

    @Autowired
    public BalanceService(Config config) {
        this.config = config;
    }

//    @Autowired
//    AuthConfig authConfig;

    public String callBalanceEndPoint(String requestXml) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("cookie", config.AUTH_HEADER);
        headers.add("SOAPAction", config.ACTION);
        HttpEntity<String> request = new HttpEntity<>(requestXml, headers);
        return restTemplate.postForObject(config.ENDPOINT, request, String.class);

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

    public GetBalanceResponse convertXmlToObject(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(GetBalanceResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (GetBalanceResponse) unmarshaller.unmarshal(new StringReader(xml));
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
                <ns5:getbalanceresponse
                	xmlns:ns5="http://www.ericsson.com/em/emm/financial/v1_2"
                	xmlns:fic="http://www.ericsson.com/em/emm/financial/v1_0/common"
                	xmlns:op="http://www.ericsson.com/em/emm/v1_0/common"
                	xmlns:xs="http://www.w3.org/2001/XMLSchema">
                	<balance>
                		<amount>1000.00</amount>
                		<currency>USD</currency>
                	</balance>
                	<positivereservations>
                		<amount>200.00</amount>
                		<currency>USD</currency>
                	</positivereservations>
                	<negativereservations>
                		<amount>50.00</amount>
                		<currency>USD</currency>
                	</negativereservations>
                	<loyalty>
                		<generated>150.00</generated>
                		<consumed>50.00</consumed>
                		<newbalance>100.00</newbalance>
                	</loyalty>
                	<overdraft>
                		<overdraft>
                			<amount>500.00</amount>
                			<currency>USD</currency>
                		</overdraft>
                		<overdraftlimit>
                			<amount>1000.00</amount>
                			<currency>USD</currency>
                		</overdraftlimit>
                		<overdraftaccessfee>
                			<amount>5.00</amount>
                			<currency>USD</currency>
                		</overdraftaccessfee>
                		<overdraftinterest>
                			<amount>10.00</amount>
                			<currency>USD</currency>
                		</overdraftinterest>
                	</overdraft>
                	<responsecode>00</responsecode>
                </ns5:getbalanceresponse>
                """;
//        String response = callBalanceEndPoint(stringRequest.toString());
//        XmlMapper mapper = new XmlMapper();
        GetBalanceResponse getBalanceResponse = null;
        try {
//            getBalanceResponse = mapper.readValue(response, GetBalanceResponse.class);
            getBalanceResponse = convertXmlToObject(response);
            ;
        } catch (Exception e) {
            System.out.println("AAAAA");
            throw e;
        }
        return getBalanceResponse;
    }
}

