package com.otelrezervasyonu.models;

public class BookingResponse {
    private int bookingid;
    private Booking booking; // geri kalan attribute'ler Booking POJO sinifimizin icindekilerle birebir ayni

    // De-Serialization yaparken Class'da default constructor yer almali. Diger siniflara tanimliyoruz.

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
