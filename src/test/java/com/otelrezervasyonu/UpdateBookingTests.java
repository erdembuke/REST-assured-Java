package com.otelrezervasyonu;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class UpdateBookingTests extends BaseTest {

    @Test
    public void updateBookingTest() {
        // Creating token because api call needs it on the header as a Cookie
        String token = createToken();

        // create reservation
        Response createBookingObject = createBooking();
        int bookingID = createBookingObject.jsonPath().getJsonObject("bookingid"); // bookingId aldik update icin

        // request
        // ***   bu method da body ve content type'i given icinde yazdik. Digerlerinde when altinda yazmistik. rest-assured
        // dokumantasyonuna gore bir farki yok given ve when kodu okunabilir yapabilmek icin eklenmistir ve iki sekilde
        // test sorunsuz bir sekilde calisabilir. Kisisel kullanim tercihi gibi denebilir.   ***
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingObject("Emrah","Guney",1993,false))
                .when()
                .put("https://restful-booker.herokuapp.com/booking/" + bookingID);

        response.prettyPrint();

        // assertions
        Assertions.assertEquals("Emrah", response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Guney", response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(1993, (Integer) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertEquals(false, response.jsonPath().getJsonObject("depositpaid"));

    }

}
