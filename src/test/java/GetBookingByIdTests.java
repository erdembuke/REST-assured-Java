import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class GetBookingByIdTests {
    // Tests might fail by the time you execute because it is an public test api and datas changing every minutes-hours.
    // Just the values in the assertions might change but coding style will stay the same.

    @Test
    public void getBookingById() {
        // create API call
        // check response, write assertions
        Response response = given() // get e kadar response oldugu icin Response degiskenine atadik
                .when()
                .get("https://restful-booker.herokuapp.com/booking/824");

        response
                .then()
                .statusCode(200);


        response.prettyPrint(); // response details

        String firstname = response.jsonPath().getJsonObject("firstname"); // firstname yi aldik
        String lastname = response.jsonPath().getJsonObject("lastname"); // lastname yi aldik
        int totalPrice = response.jsonPath().getJsonObject("totalprice"); // price yi aldik
        boolean deposit = response.jsonPath().getJsonObject("depositpaid"); // deposit

        // JUnit Assertions.
        Assertions.assertEquals("John", firstname);
        Assertions.assertEquals("Smith", lastname);
        Assertions.assertEquals(111, totalPrice);
        Assertions.assertTrue(deposit);


    }
}
