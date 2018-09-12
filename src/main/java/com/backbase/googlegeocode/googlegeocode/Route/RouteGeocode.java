package com.backbase.googlegeocode.googlegeocode.Route;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.apache.camel.component.http4.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class RouteGeocode extends RouteBuilder {
    @Value("${google.geocode.api.url}")
    private String pathUrl;

    @Override
    public void configure() {

        from("direct:start")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setHeader(Exchange.HTTP_QUERY, simple("key=${in.headers.key}"))
                .setHeader(Exchange.HTTP_QUERY, simple("address=${in.headers.address}"))
                .to(pathUrl);

    }
}