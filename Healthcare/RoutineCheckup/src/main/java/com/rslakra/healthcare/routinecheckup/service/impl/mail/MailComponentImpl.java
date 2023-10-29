package com.rslakra.healthcare.routinecheckup.service.impl.mail;


import com.rslakra.healthcare.routinecheckup.service.mail.MailComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:21 PM
 */
@Component
@RequiredArgsConstructor
public class MailComponentImpl implements MailComponent {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}

