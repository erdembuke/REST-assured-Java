import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class GetAllBookingsTests {
    // Create API Call
    // https://restful-booker.herokuapp.com/booking (GET)
    // Check the Response

    @Test
    public void getAllBookingsTest() {
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking") // URL
                .then()
                .log().all()
                .statusCode(200);
    }

}
