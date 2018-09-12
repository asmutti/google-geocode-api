package com.backbase.googlegeocode.googlegeocode.controller;

import org.apache.camel.*;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GoogleGeocodeController {

    @Autowired
    @EndpointInject(uri = "direct:start")
    private ProducerTemplate producer;

    @Value("${google.maps.api.key}")
    private String key;

    /**
     * Call Google Geolocation XML API with a private key.
     * @param address
     * @return
     */
    @GetMapping("/geocode/{address}")
    @ResponseBody
    public String getGeoLocation(@PathVariable(name = "address") String address) {

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("key", key);
        uriVariables.put("address", address);

        Exchange exchange = producer.send("direct:start", new Processor() {
            @Override
            public void process(Exchange exchange) {
                exchange.getIn().setHeaders(uriVariables);
            }
        });
        
        String responseXML = exchange.getOut().getBody(String.class);

        JSONObject responseJson = XML.toJSONObject(responseXML);

        return responseJson.toString(4);

    }

}
