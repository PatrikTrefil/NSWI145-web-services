package com.patriktrefil.soap;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        String bookingUri = "http://127.0.0.1:8000/booking";
        System.out.println("Publishing Booking at " + bookingUri);
        Endpoint.publish(bookingUri, new BookingImpl());
        String accountingUri = "http://127.0.0.1:8000/accounting";
        System.out.println("Publishing Accounting at " + accountingUri);
        Endpoint.publish(accountingUri, new AccountingImpl());
    }
}