package com.rslakra.alertservice.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 19:56 AM
 */
@Getter
@Setter
@NoArgsConstructor
public class Alert {

    private Long id;
    private AlertType alertType;
    private String status;
    private Date expiredOn;
    private AlertParams alertParams;
    private AlertEmails alertEmails;
    private AlertEmailParams alertEmailParams;
    private String emailStatus;
    private String sourceId;
    private SourceType sourceType;
    private String resourceType;
}
