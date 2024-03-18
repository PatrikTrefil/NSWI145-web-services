package com.patriktrefil.soap;

import javax.jws.WebService;

@WebService(targetNamespace = "http://soap.patriktrefil.com/", endpointInterface = "com.patriktrefil.soap.Accounting", portName = "AccountingImplPort", serviceName = "AccountingImplService")
public class AccountingImpl implements Accounting {

	@Override
	public boolean createInvoice(String familyName, String giveName, float amountInEur) {
		System.out.println(
                "Creating invoice for " + giveName + " " + familyName + " in the amount of " + amountInEur + " EUR.");
        return true;
	}

	@Override
	public boolean payInvoice(int invoiceNumber) {
		 System.out.println("Paying invoice " + invoiceNumber + ".");
	     return true;
	}

}
