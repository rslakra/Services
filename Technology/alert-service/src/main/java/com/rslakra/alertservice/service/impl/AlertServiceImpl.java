package com.rslakra.alertservice.service.impl;

import com.rslakra.alertservice.persistence.domain.Alert;
import com.rslakra.alertservice.service.AlertService;

import java.util.List;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 9/15/23 2:38 PM
 */
public class AlertServiceImpl implements AlertService {

    /**
     * Sends an alert to from the sender to the recipient.
     *
     * @param senderId
     * @param recipientId
     * @param message
     * @return
     */
    @Override
    public boolean sendMessage(UUID senderId, UUID recipientId, String message) {
        return false;
    }

    /**
     * Returns all the new alerts of the logged-in user.
     *
     * @return
     */
    @Override
    public List<Alert> receiveNewMessages() {
        return null;
    }

    /**
     * Returns all the alerts of the logged-in user.
     *
     * @return
     */
    @Override
    public List<Alert> receiveMessages() {
        return null;
    }

    /**
     * Returns the alert history of the given sender based on the timestamp.
     *
     * @param senderId
     * @param timeStamp
     * @return
     */
    @Override
    public List<Alert> getDialogs(UUID senderId, Long timeStamp) {
        return null;
    }

    /**
     * Returns the alert history of the sender and the receiver.
     *
     * @param senderId
     * @param recipientId
     * @return
     */
    @Override
    public List<Alert> getHistory(UUID senderId, UUID recipientId) {
        return null;
    }
}
