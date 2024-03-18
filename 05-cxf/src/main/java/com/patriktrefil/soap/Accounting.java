package com.patriktrefil.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Accounting", targetNamespace = "http://soap.patriktrefil.com/")
public interface Accounting {
    @WebMethod(operationName = "createInvoice", action = "urn:CreateInvoice")
	boolean createInvoice(String familyName, String giveName, float amountInEur);
    @WebMethod(operationName = "payInvoice", action = "urn:PayInvoice")
	boolean payInvoice(int invoiceNumber);
}
