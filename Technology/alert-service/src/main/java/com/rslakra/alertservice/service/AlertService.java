package com.rslakra.alertservice.service;

import com.rslakra.alertservice.persistence.domain.Alert;

import java.util.List;
import java.util.UUID;

/**
 * The Service that handles the trigger, does the core logic and is capable of sending and/or persisting notifications.
 *
 * @author Rohtash Lakra
 * @created 12/5/22 19:01 PM
 */
public interface AlertService {

    /**
     * Sends an alert to from the sender to the recipient.
     *
     * @param senderId
     * @param recipientId
     * @param message
     * @return
     */
    public boolean sendMessage(UUID senderId, UUID recipientId, String message);

    /**
     * Returns all the new alerts of the logged-in user.
     *
     * @return
     */
    public List<Alert> receiveNewMessages();

    /**
     * Returns all the alerts of the logged-in user.
     *
     * @return
     */
    public List<Alert> receiveMessages();

    /**
     * Returns the alert history of the given sender based on the timestamp.
     *
     * @param senderId
     * @param timeStamp
     * @return
     */
    public List<Alert> getDialogs(UUID senderId, Long timeStamp);

    /**
     * Returns the alert history of the sender and the receiver.
     *
     * @param senderId
     * @param recipientId
     * @return
     */
    public List<Alert> getHistory(UUID senderId, UUID recipientId);

}
