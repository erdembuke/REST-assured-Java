package com.otelrezervasyonu;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseTest {

    // body kismi string kabul ettigi icin method donus tipi string olacak
    // rezervasyon degerlerinin dinamik olabilmesi icin, olusturulurken degistirilebilmesi icin parametreler verildi
    protected String bookingObject(String firstname, String lastname, int totalPrice) {
        JsonObject body = new JsonObject();
        body.add("firstname", firstname);
        body.add("lastname", lastname);
        body.add("totalprice", totalPrice);
        body.add("depositpaid", true);

        JsonObject bookingDates = new JsonObject(); // bookingdates kendi basina bir obje oldugu icin ayri obje olusturduk
        bookingDates.add("checkin", "2024-01-01");
        bookingDates.add("checkout", "2024-02-02");

        body.add("bookingdates", bookingDates); // objeyi value olarak body ye ekledik
        body.add("additionalneeds", "Pet Room");

        return body.toString();
    }

    protected Response createBooking() {
        Response response = given()

                .when()
                .contentType(ContentType.JSON) // api call needs to add contentype as a header, in documentation it says
                .body(bookingObject("Erdem","Buke",100)) // BaseTest classda olusturdugumuz json object i method ile cagirdik
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint(); // seeing response as output in console

        // Status code assertion, (then part)
        response.then()
                .statusCode(200);

        return response;

    }
}
