package com.patriktrefil.soap;

@javax.jws.WebService(endpointInterface = "com.patriktrefil.soap.Accounting")
public class AccountingImpl implements Accounting {
    @Override
    public boolean createInvoice(String familyName, String giveName, float amountInEur) {
        System.out.println("Creating invoice for " + giveName + " " + familyName + " in the amount of " + amountInEur + " EUR.");
        return true;
    }
}
