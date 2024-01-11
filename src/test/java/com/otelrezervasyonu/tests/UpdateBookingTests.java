package com.otelrezervasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class UpdateBookingTests extends BaseTest {

    @Test
    public void updateBookingTest() {
        // request
        // ***   bu method da body ve content type'i given icinde yazdik. Digerlerinde when altinda yazmistik. rest-assured
        // dokumantasyonuna gore bir farki yok given ve when kodu okunabilir yapabilmek icin eklenmistir ve iki sekilde
        // test sorunsuz bir sekilde calisabilir. Kisisel kullanim tercihi gibi denebilir.   ***
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken()) // we can use createToken(); directly here
                .body(bookingObject("Emrah","Guney",1993,false))
                .when()
                .put("/booking/" + createBookingId());


        // assertions
        Assertions.assertEquals("Emrah", response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Guney", response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(1993, (Integer) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertEquals(false, response.jsonPath().getJsonObject("depositpaid"));

    }

}
