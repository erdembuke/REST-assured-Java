import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CreateBookingTests {

    @Test
    public void createBooking() {
        /* create body
        curl -X POST \
        https://restful-booker.herokuapp.com/booking \
        -H 'Content-Type: application/json' \
        -d '{
        "firstname" : "Jim",
        "lastname" : "Brown",
        "totalprice" : 111,
        "depositpaid" : true,
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Breakfast"
        }'
         */
        // make the API call
        // write Assertions

        JsonObject body = new JsonObject();
        body.add("firstname", "Erdem");
        body.add("lastname", "Buke");
        body.add("totalprice", 100);
        body.add("depositpaid", true);

        JsonObject bookingDates = new JsonObject(); // bookingdates kendi basina bir obje oldugu icin ayri obje olusturduk
        bookingDates.add("checkin", "2024-01-01");
        bookingDates.add("checkout", "2024-02-02");

        body.add("bookingdates", bookingDates); // objeyi value olarak body ye ekledik
        body.add("additionalneeds", "Pet Room");

        // API call , Response
        Response response = given()

                .when()
                .contentType(ContentType.JSON) // api call needs to add contentype as a header, in documentation it says
                .body(body.toString()) // toString yapmazsak hata alacagiz
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint(); // seeing response as output in console

        // Status code assertion, (then part)
        response.then()
                .statusCode(200);

        // Assertions
        Assertions.assertEquals("Erdem", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Buke", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(100, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
        // boolean da cannot cast oldugu icin AssertTrue yerine equals ile yaptim
        Assertions.assertEquals("Pet Room", response.jsonPath().getJsonObject("booking.additionalneeds"));
    }
}
