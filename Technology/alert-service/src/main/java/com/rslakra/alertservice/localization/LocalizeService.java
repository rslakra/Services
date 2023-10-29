package com.rslakra.alertservice.localization;

/**
 * The <code>LocalizeService</code> service for translations. For example, we don’t want to persist a bunch of text in
 * DB for showing notification history in the client app, but we want the app to build the text depending on the
 * language and other parameters after the history is fetched to the client app. Same for the backend, for sending
 * localized push we need to build localized push notifications using our translations. As we want to have localized
 * notifications I assume our infrastructure already supports localization, and we already should be using some custom
 * build or 3rd party service. In my case, our company already had localization service.
 *
 * @author Rohtash Lakra
 * @created 9/15/23 11:52 AM
 */
public interface LocalizeService {

}
