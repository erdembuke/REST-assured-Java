package com.otelrezervasyonu;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CreateBookingTests extends BaseTest{

    @Test
    public void createBookingTest() {
        // create body (created with inheritence)
        // make the API call
        // write Assertions

        // API call , Response
        Response response = createBooking(); // response objesine ihtiyacimiz var o yuzden response return eden method

        // Assertions
        Assertions.assertEquals("Erdem", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Buke", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(100, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
        // boolean da cannot cast oldugu icin AssertTrue yerine equals ile yaptim
        Assertions.assertEquals("Pet Room", response.jsonPath().getJsonObject("booking.additionalneeds"));
    }
}
