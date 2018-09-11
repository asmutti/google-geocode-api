package com.backbase.googlegeocode.googlegeocode.Controller;

import com.backbase.googlegeocode.googlegeocode.controller.GoogleGeocodeController;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GoogleGeocodeController.class, secure = false)
public class GoogleGeocodeControllerTest  {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void retrieveAddress() throws Exception {

        boolean expected = false;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("geocode/Jacob Bontiusplaats 9 1018 LL Amsterdam")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        if(result.toString().contains("\"status\":\"OK\"")) {
            expected = true;
        }

        Assert.assertTrue(expected);
    }
}
