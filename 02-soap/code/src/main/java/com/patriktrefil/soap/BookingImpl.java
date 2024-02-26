package com.patriktrefil.soap;

/**
 * Mock implementation of the Booking service.
 * It allows booking of room 1 and 2 for any date.
 */
@javax.jws.WebService(endpointInterface = "com.patriktrefil.soap.Booking")
public class BookingImpl implements Booking {
    @Override
    public boolean bookRoom(int roomNumber, String isoStartDate, String isoEndDate) {
        if (roomNumber == 1 || roomNumber == 2) {
            System.out.println("Booking room " + roomNumber + " from " + isoStartDate + " to " + isoEndDate + ".");
            return true;
        } else {
            System.out.println("Can't book room " + roomNumber + " from " + isoStartDate + " to " + isoEndDate + ".");
            return false;
        }
    }
}
