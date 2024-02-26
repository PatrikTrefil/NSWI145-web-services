package com.patriktrefil.soap;

@javax.jws.WebService
public interface Accounting {
    @javax.jws.WebMethod
    boolean createInvoice(String familyName, String giveName, float amountInEur);
}
