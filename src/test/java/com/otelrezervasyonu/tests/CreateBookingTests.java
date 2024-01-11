package com.otelrezervasyonu.tests;

import com.otelrezervasyonu.models.Booking;
import com.otelrezervasyonu.models.BookingDates;
import com.otelrezervasyonu.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class CreateBookingTests extends BaseTest{

    @Test
    public void createBookingTest() {
        // cagiriyi gerceklestir

        Response response = createBooking(); // response objesine ihtiyacimiz var o yuzden response return eden method

        // Assertions
        Assertions.assertEquals("Erdem", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Buke", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(100, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
        // boolean da cannot cast oldugu icin AssertTrue yerine equals ile yaptim
        Assertions.assertEquals("Pet Room", response.jsonPath().getJsonObject("booking.additionalneeds"));
    }

    @Test
    public void createBookingWithPojo() {
        // Serialization: Nesnenin durumunun saklanmasi ve daha sonra ayni durumun tekrar kullanilmasi
        // BookingDates POJO
        BookingDates bookingDates = new BookingDates("2024-01-01","2024-02-02");
        // Booking POJO
        Booking booking = new Booking("Erdem","Buke",250,false,bookingDates,
                "Pet Area");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking) // POJO yu JSON a cevirme kutuphane ile otomatik yapiliyor
                .when()
                .post("/booking");

        // De-Serialization. Gelen Response'u BookingResponse class icine yaziyoruz
        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse + " Booking Response Kaydedildi");
        // ciktinin okunakli olmasi icin classlara toString() method eklendi

        // Assertion
        response
                .then()
                .statusCode(200);

        // De-Serialization ile Assertion yazimi
        Assertions.assertEquals("Erdem", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Buke", bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(250, bookingResponse.getBooking().getTotalprice());
        Assertions.assertEquals(false, bookingResponse.getBooking().getDepositpaid());
        Assertions.assertEquals("2024-01-01", bookingResponse.getBooking().getBookingdates().getCheckin());
        Assertions.assertEquals("2024-02-02", bookingResponse.getBooking().getBookingdates().getCheckout());
        Assertions.assertEquals("Pet Area", bookingResponse.getBooking().getAdditionalneeds());



    }


}
