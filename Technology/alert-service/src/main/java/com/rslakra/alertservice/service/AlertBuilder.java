package com.rslakra.alertservice.service;

import com.rslakra.alertservice.persistence.domain.Alert;
import com.rslakra.alertservice.persistence.domain.AlertEmailParams;
import com.rslakra.alertservice.persistence.domain.AlertEmails;
import com.rslakra.alertservice.persistence.domain.AlertParams;
import com.rslakra.alertservice.persistence.domain.AlertType;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 20:12 PM
 */
public class AlertBuilder {

    private final Alert alert = new Alert();
    private String alertName;
    private String userName;

    public AlertBuilder setAlertType(AlertType alertType) {
        alert.setAlertType(alertType);
        return this;
    }

    public AlertBuilder setAlertParams(AlertParams alertParams) {
        alert.setAlertParams(alertParams);
        return this;
    }

    public AlertBuilder setAlertEmails(AlertEmails alertEmails) {
        alert.setAlertEmails(alertEmails);
        return this;
    }

    public AlertBuilder setAlertEmailParams(AlertEmailParams alertEmailParams) {
        alert.setAlertEmailParams(alertEmailParams);
        return this;
    }
}
