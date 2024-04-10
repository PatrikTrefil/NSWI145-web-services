package com.patriktrefil.soap;

@javax.jws.WebService(endpointInterface = "com.patriktrefil.soap.Email")
public class EmailImpl implements Email {
    @Override
    public boolean sendEmail(String reciever, String content) {
        System.out.println("Sending email to " + reciever + " with content: " + content);
        return true;
    }
}
