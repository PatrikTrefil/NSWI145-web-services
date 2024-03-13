package com.patriktrefil.webservices;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

/**
 * Servlet implementation class IntermediaryServlet
 */
@WebServlet("/IntermediaryServlet")
public class IntermediaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntermediaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	final String endpoint = "http://localhost:8000/accounting";
	final String namespaceURI = "http://soap.patriktrefil.com/";
	final float czkToEurConversionRate = 25.27f;
	final String czkCode = "CZK";
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MessageFactory mf = MessageFactory.newInstance();
	        SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapc = soapcf.createConnection();
			SOAPMessage soapm = mf.createMessage(null, request.getInputStream());
			SOAPHeader soaph = soapm.getSOAPHeader();
			
			QName currencyHeaderName = new QName("", "currency");
			Iterator<SOAPHeaderElement> headerElementsIterator;
			if (soaph != null) headerElementsIterator = soaph.getChildElements(currencyHeaderName);
			else headerElementsIterator = Collections.emptyIterator();
			String currency = null;
			if (headerElementsIterator.hasNext()) {
				currency = headerElementsIterator.next().getValue();
			}
			if (czkCode.equals(currency)) {
				QName createInvoiceName = new QName(namespaceURI, "createInvoice", "soap");
				SOAPBodyElement createInvoiceElement = (SOAPBodyElement) soapm.getSOAPBody().getChildElements(createInvoiceName).next();
				QName priceName = new QName("", "arg2");
				SOAPBodyElement priceElement = (SOAPBodyElement) createInvoiceElement.getChildElements(priceName).next();
				float priceInCzk = Float.parseFloat(priceElement.getValue());
				// Modify price in message to use EUR
				float priceInEur = priceInCzk / czkToEurConversionRate;
				priceElement.setValue(String.valueOf(priceInEur));
			}
			
	        SOAPMessage bookingResponse = soapc.call(soapm, endpoint);
	        soapc.close();
	        
	        if (czkCode.equals(currency)) {
	        	SOAPEnvelope envelope = bookingResponse.getSOAPPart().getEnvelope();
        		SOAPHeader responseHeader = bookingResponse.getSOAPHeader();
	        	if (responseHeader == null) {
	        		responseHeader = envelope.addHeader();
	        	}
	        	responseHeader.addChildElement(new QName(namespaceURI, "sourceCurrency")).addTextNode(currency);
	        	responseHeader.addChildElement(new QName(namespaceURI, "conversionRateToEur")).addTextNode(String.valueOf(czkToEurConversionRate));
	        }
	        
			bookingResponse.writeTo(response.getOutputStream());
		} catch (SOAPException e) {
			throw new ServletException(e.getMessage());
		}

	}

}
