package com.otelrezervasyonu.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.*;

public class BaseTest {

    // tum test kosumlari oncesi bir kez kosulacak
    RequestSpecification spec; // kullanacagimiz icin sinif duzeninde tanimladik
    @BeforeEach
    public void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                // cagrilarin loglanmasi icin kutuphanedeki method
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    protected int createBookingId() {
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }

    protected Response createBooking() {
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON) // api call needs to add contentype as a header, in documentation it says
                .body(bookingObject("Erdem","Buke",100,true)) // BaseTest classda olusturdugumuz json object i method ile cagirdik
                .post("/booking");

        // Status code assertion, (then part)
        response
                .then()
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

        JSONObject bookingDates = new JSONObject(); // bookingdates kendi basina bir obje oldugu icin ayri obje olusturduk
        bookingDates.put("checkin", "2024-01-01");
        bookingDates.put("checkout", "2024-02-02");

        body.put("bookingdates", bookingDates); // objeyi value olarak body ye ekledik
        body.put("additionalneeds", "Pet Room");

        return body.toString();
    }

    protected String createToken() {
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)

                .when()
                .body(body.toString()) // json object i ekledik
                .post("/auth");

        response.prettyPrint();

        return response.jsonPath().getJsonObject("token"); // token degerini string cinsinden donecek
    }
}
