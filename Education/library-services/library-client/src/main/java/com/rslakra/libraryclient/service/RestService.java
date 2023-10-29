package com.rslakra.libraryclient.service;

import com.devamatre.framework.core.BeanUtils;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 4:50 PM
 */
//@Service
public final class RestService extends RestTemplate {

    private final String apiHostBaseUrl;

    /**
     *
     */
    public RestService(final String apiHostBaseUrl) {
        super();
        this.apiHostBaseUrl = apiHostBaseUrl;
    }

    /**
     * @param requestFactory
     */
    public RestService(final String apiHostBaseUrl, ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
        this.apiHostBaseUrl = apiHostBaseUrl;
    }

    /**
     * @param messageConverters
     */
    public RestService(final String apiHostBaseUrl, List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
        this.apiHostBaseUrl = apiHostBaseUrl;
    }

    /**
     * @return
     */
    public String getApiHostBaseUrl() {
        return apiHostBaseUrl;
    }

    /**
     * Return the path s
     *
     * @param pathSegment
     * @return
     */
    public String hostPathUrl(final String pathSegment) {
        return BeanUtils.pathSegments(getApiHostBaseUrl(), pathSegment);
    }

}
