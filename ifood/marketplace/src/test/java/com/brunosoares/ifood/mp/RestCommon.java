package com.brunosoares.ifood.mp;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestCommon {

    public static RequestSpecification given() {
        return RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
    }

}