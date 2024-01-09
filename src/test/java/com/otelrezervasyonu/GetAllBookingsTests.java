package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void getAllBookingsByQueryParam() {
        // creating new reservation
        int id = createBookingId();
        // add query param
        spec.queryParam("firstname","Erdem");
        spec.queryParam("lastname","Buke");
        // make api call
        Response response = given(spec)
                .when()
                .get("/booking");

        // assertion
        response.then()
                .statusCode(200);

        // response'da bulunan butun id leri liste olusturup icine ekledik
        List<Integer> filteredReservations = response.jsonPath().getList("bookingid");
        // listedeki elemanlari kontrol edip istedigimiz reservation id yi bul
        Assertions.assertTrue(filteredReservations.contains(id));

    }

}
