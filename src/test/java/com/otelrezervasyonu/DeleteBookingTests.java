package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class DeleteBookingTests extends BaseTest{

    @Test
    public void deleteBookingTest() {
        // token ve rezervasyon olusturmayi BaseTest class icindeki methodlar ile yapabiliyoruz. response icinde
        // bu methodlari kullanarak testimizi gerceklestirebilecegiz
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/" + createBookingId());


        // assertion
        response.then()
                .statusCode(201);




    }
}