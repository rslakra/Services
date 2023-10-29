package com.rslakra.alertservice.service;

/**
 * The <code>AlertEngine</code> or <code>notification</code> engine is a little processor that does all the
 * notification-related routines, such as calling notification service for creating notification and notification
 * recipient records in the database; calling templating and translation services for preparing notification payloads;
 * calling notification transport module (channel providers) for sending the actual notifications if needed; or even
 * knows how to handle common exceptions that might happen during the flow.
 *
 * @author Rohtash Lakra
 * @created 9/15/23 12:00 PM
 */
public interface AlertEngine {

}
