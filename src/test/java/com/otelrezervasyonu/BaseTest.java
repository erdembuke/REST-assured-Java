package com.otelrezervasyonu;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected int createBookingId() {
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }

    protected Response createBooking() {
        Response response = given()

                .when()
                .contentType(ContentType.JSON) // api call needs to add contentype as a header, in documentation it says
                .body(bookingObject("Erdem","Buke",100,true)) // BaseTest classda olusturdugumuz json object i method ile cagirdik
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint(); // seeing response as output in console

        // Status code assertion, (then part)
        response.then()
                .statusCode(200);

        return response;

    }

    // body kismi string kabul ettigi icin method donus tipi string olacak
    // rezervasyon degerlerinin dinamik olabilmesi icin, olusturulurken degistirilebilmesi icin parametreler verildi
    protected String bookingObject(String firstname, String lastname, int totalPrice, boolean depositPaid) {
        JSONObject body = new JSONObject();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);

        JsonObject bookingDates = new JsonObject(); // bookingdates kendi basina bir obje oldugu icin ayri obje olusturduk
        bookingDates.add("checkin", "2024-01-01");
        bookingDates.add("checkout", "2024-02-02");

        body.put("bookingdates", bookingDates); // objeyi value olarak body ye ekledik
        body.put("additionalneeds", "Pet Room");

        return body.toString();
    }

    protected String createToken() {
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");

        Response response = given()
                .contentType(ContentType.JSON)

                .when()
                .body(body.toString()) // json object i ekledik
                .log().all()
                .post("https://restful-booker.herokuapp.com/auth");

        response.prettyPrint();

        return response.jsonPath().getJsonObject("token"); // token degerini string cinsinden donecek
    }
}
