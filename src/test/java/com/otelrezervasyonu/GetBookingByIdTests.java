package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class GetBookingByIdTests extends BaseTest{
    // Tests might fail by the time you execute because it is an public test api and datas changing every minutes-hours.
    // Just the values in the assertions might change but coding style will stay the same.

    @Test
    public void getBookingById() {
        // make API call
        Response response = given() // get e kadar response oldugu icin Response degiskenine atadik
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + createBookingId()); // dinamik id yi ekledik

        response
                .then()
                .statusCode(200);


        response.prettyPrint(); // response details

        // Degiskenlere atamadan direkt response.jsonPath()getJsonObject(...) diye de daha sade yazabiliriz
        String firstname = response.jsonPath().getJsonObject("firstname"); // firstname yi aldik
        String lastname = response.jsonPath().getJsonObject("lastname"); // lastname yi aldik
        int totalPrice = response.jsonPath().getJsonObject("totalprice"); // price yi aldik
        boolean deposit = response.jsonPath().getJsonObject("depositpaid"); // deposit

        // JUnit Assertions.
        Assertions.assertEquals("Erdem", firstname);
        Assertions.assertEquals("Buke", lastname);
        Assertions.assertEquals(100, totalPrice);
        Assertions.assertTrue(deposit);


    }
}
