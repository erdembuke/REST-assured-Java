package com.otelrezervasyonu;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class GetAllBookingsTests extends BaseTest{
    // Create API Call
    // https://restful-booker.herokuapp.com/booking (GET)
    // Check the Response

    @Test
    public void getAllBookings() {
        given(spec)
                .when()
                .get("/booking") // URL
                .then()
                .statusCode(200);
    }

}
