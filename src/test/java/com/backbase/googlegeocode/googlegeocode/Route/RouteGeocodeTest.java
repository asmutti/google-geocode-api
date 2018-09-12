package com.backbase.googlegeocode.googlegeocode.Route;

import com.backbase.googlegeocode.googlegeocode.controller.GoogleGeocodeController;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
public class RouteGeocodeTest {


    @Autowired
    private ProducerTemplate template;

    @Value("${google.maps.api.key}")
    private String key;

    /**
     * Testing the route directly
     */
    @Ignore
    @Test
    public void testGeocodeRoute() {

        String address = "Jacob Bontiusplaats 9 1018 LL Amsterdam";
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("key", key);
        uriVariables.put("address", address);

        Exchange exchange = template.send("direct:start", new Processor() {
            @Override
            public void process(Exchange exchange) {
                exchange.getIn().setHeaders(uriVariables);
            }
        });


        //the API is intermittent, so this test is not reliable.
        Assert.assertTrue(exchange.getOut().getBody(String.class).contains("\"status\":\"OK\""));
    }
}
