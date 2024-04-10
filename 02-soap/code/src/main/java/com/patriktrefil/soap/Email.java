package com.patriktrefil.soap;

@javax.jws.WebService
public interface Email {
    @javax.jws.WebMethod
    boolean sendEmail(String reciever, String content);
}
