package com.rslakra.healthcare.routinecheckup.service.mail;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:23 PM
 */
public interface MailComponent {

    void sendMessage(String to, String subject, String body);

}
