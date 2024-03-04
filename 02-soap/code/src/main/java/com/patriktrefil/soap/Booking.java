package com.patriktrefil.soap;

@javax.jws.WebService
public interface Booking {
    @javax.jws.WebMethod
    boolean bookRoom(int roomNumber, String isoStartDate, String isoEndDate);

    @javax.jws.WebMethod
    boolean cancelBooking(int bookingNumber);
}
