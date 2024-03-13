package com.patriktrefil.mff.webservices;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

public class Main {
        /**
         * Books room 1 from 1.1.2023 to 2.1.2023.
         */
        public static void main(String[] args) throws SOAPException {
                final String roomNumber = "1";
                final String startDate = "1.1.2023";
                final String endDate = "2.1.2023";
                final String endpoint = "http://localhost:8000/booking";
                SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
                SOAPConnection soapc = soapcf.createConnection();

                MessageFactory mf = MessageFactory.newInstance();
                SOAPMessage soapm = mf.createMessage();

                SOAPPart soapp = soapm.getSOAPPart();
                SOAPEnvelope soape = soapp.getEnvelope();
                SOAPBody soapb = soape.getBody();

                String namespaceURI = "http://soap.patriktrefil.com/";
                soape.getHeader().detachNode();
                QName name = new QName(namespaceURI, "bookRoom");
                SOAPElement soapel = soapb.addBodyElement(name);
                // parameters
                soapel.addChildElement(
                                new QName("", "arg0")).addTextNode(roomNumber);
                soapel.addChildElement(
                                new QName("", "arg1")).addTextNode(startDate);
                soapel.addChildElement(
                                new QName("", "arg2")).addTextNode(endDate);

                SOAPMessage response = soapc.call(soapm, endpoint);
                soapc.close();
                // Process response
                SOAPBody responseBody = response.getSOAPBody();
                if (responseBody.hasFault()) {
                        System.out.println(responseBody.getFault().getFaultString());
                } else {
                        QName bookRoomResponseName = new QName(namespaceURI, "bookRoomResponse");

                        SOAPBodyElement bookRoomResponse = (SOAPBodyElement) responseBody
                                        .getChildElements(bookRoomResponseName).next();

                        QName returnName = new QName("", "return");
                        SOAPElement returnElement = (SOAPElement) bookRoomResponse.getChildElements(returnName).next();

                        System.out.println(returnElement.getValue());
                }
        }
}