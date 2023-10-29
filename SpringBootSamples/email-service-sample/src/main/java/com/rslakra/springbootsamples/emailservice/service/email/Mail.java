package com.rslakra.springbootsamples.emailservice.service.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 4:46 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class Mail {

    private String subject;
    private String from;
    private String to;
    private MailModel model;

}
