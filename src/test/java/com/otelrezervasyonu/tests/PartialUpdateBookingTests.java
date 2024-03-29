package com.otelrezervasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class PartialUpdateBookingTests extends BaseTest {

    @Test
    public void partialUpdateBookingTest() {
        // create reservation and get booking id
        // we simplified the code if we compare it with the previous one (see updateBookingTests)
        // api call
        JSONObject body = new JSONObject();
        body.put("firstname","Seyma");
        body.put("lastname","Dalar");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken()) // get token with method
                .header("Accept","application/json")
                .body(body.toString())
                .when()
                .patch("/booking/" + createBookingId());


        // assertions
        Assertions.assertEquals("Seyma",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Dalar",response.jsonPath().getJsonObject("lastname"));
    }
}
