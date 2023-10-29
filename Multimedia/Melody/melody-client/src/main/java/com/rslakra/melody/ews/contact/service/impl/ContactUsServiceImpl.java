package com.rslakra.melody.ews.contact.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.client.ApiRestClient;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.persistence.Operation;
import com.rslakra.melody.ews.contact.payload.ContactUs;
import com.rslakra.melody.ews.contact.service.ContactUsService;
import com.rslakra.melody.ews.framework.client.impl.AbstractClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 10:06 AM
 */
@Service
public class ContactUsServiceImpl extends AbstractClientServiceImpl<ContactUs> implements ContactUsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsServiceImpl.class);

    // apiRestClient
    private final ApiRestClient apiRestClient;

    /**
     * @param apiRestClient
     */
    @Autowired
    public ContactUsServiceImpl(final ApiRestClient apiRestClient) {
        LOGGER.debug("ContactUsServiceImpl({})", apiRestClient);
        this.apiRestClient = apiRestClient;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param contactUs
     * @return
     */
    @Override
    public ContactUs validate(Operation operation, ContactUs contactUs) {
        switch (operation) {
            case CREATE:
                if (BeanUtils.isEmpty(contactUs.getEmail())) {
                    throw new InvalidRequestException("The contactUs's email should provide!");
                } else if (BeanUtils.isEmpty(contactUs.getFirstName())) {
                    throw new InvalidRequestException("The contactUs's firstName should provide!");
                } else if (BeanUtils.isEmpty(contactUs.getLastName())) {
                    throw new InvalidRequestException("The contactUs's lastName should provide!");
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(contactUs.getId())) {
                    throw new InvalidRequestException("The contactUs's ID should provide!");
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        return contactUs;
    }

    /**
     * @param contactUs
     * @return
     */
    @Override
    public ContactUs create(ContactUs contactUs) {
        LOGGER.debug("+create({})", contactUs);
        if (BeanUtils.isNull(contactUs)) {
            throw new InvalidRequestException("The contactUs should provide!");
        }

        validate(Operation.CREATE, contactUs);
        contactUs = apiRestClient.doPost(null, contactUs, ContactUs.class);
        LOGGER.debug("-create(), contactUs: {}", contactUs);
        return contactUs;
    }

    /**
     * @param contactUss
     * @return
     */
    @Override
    public List<ContactUs> create(List<ContactUs> contactUss) {
        LOGGER.debug("+create({})", contactUss);
        if (BeanUtils.isEmpty(contactUss)) {
            throw new InvalidRequestException("The contactUss should provide!");
        }

        contactUss.forEach(contactUs -> validate(Operation.CREATE, contactUs));
        contactUss = apiRestClient.doPost(null, contactUss, List.class);
        LOGGER.debug("-create(), contactUss:{}", contactUss);
        return contactUss;
    }

    /**
     * @return
     */
    @Override
    public List<ContactUs> getAll() {
        LOGGER.debug("+getAll()");
        // note: get results by array and convert to list.
        List<ContactUs> contactUss;
        // helps to display empty ui page.
        try {
            contactUss = Arrays.asList(apiRestClient.doGet(null, ContactUs[].class));
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            // helps to display empty ui page.
            contactUss = new ArrayList<>();
        }

        LOGGER.debug("-getAll(), contactUss:{}", contactUss);
        return contactUss;
    }

    /**
     * @param filters
     * @return
     */
    @Override
    public List<ContactUs> getByFilter(Map<String, Object> filters) {
        LOGGER.debug("+getByFilter({})", filters);
        // rest/contactUss/filter?id=3
        final ContactUs[] contactUss = apiRestClient.doGet(null, ContactUs[].class, filters);
        LOGGER.debug("-getByFilter(), contactUss:{}", contactUss);
        return Arrays.asList(contactUss);
    }

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Override
    public List<ContactUs> getByFilter(Map<String, Object> filters, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", filters, pageable);
        List<ContactUs> contactUss = getByFilter(filters);
        LOGGER.debug("-getByFilter(), contactUss:{}", contactUss);
        return contactUss;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public ContactUs getById(Long id) {
        LOGGER.debug("+getById({})", id);
        // rest/contactUss/filter?id=16
        ContactUs
            contactUs =
            (ContactUs) getByFilter(Payload.newBuilder().ofPair("id", id)).stream().findFirst().orElse(null);
        LOGGER.debug("-getById(), contactUs:{}", contactUs);
        return contactUs;
    }

    /**
     * @param contactUs
     * @return
     */
    @Override
    public ContactUs update(ContactUs contactUs) {
        LOGGER.debug("+update({})", contactUs);
        if (BeanUtils.isEmpty(contactUs)) {
            throw new InvalidRequestException("The contactUs should provide!");
        }

        validate(Operation.UPDATE, contactUs);
        apiRestClient.doPut(null, contactUs, ContactUs.class);

        LOGGER.debug("-update(), contactUs:{}", contactUs);
        return contactUs;
    }

    /**
     * @param contactUss
     * @return
     */
    @Override
    public List<ContactUs> update(List<ContactUs> contactUss) {
        LOGGER.debug("+update({})", contactUss);
        if (BeanUtils.isEmpty(contactUss)) {
            throw new InvalidRequestException("The contactUs should provide!");
        }

        contactUss.forEach(contactUs -> validate(Operation.UPDATE, contactUs));
        apiRestClient.doPut(null, contactUss, List.class);

        LOGGER.debug("-update(), contactUss:{}", contactUss);
        return contactUss;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ContactUs delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The contactUs's id should provide!");
        ContactUs contactUs = null;
        apiRestClient.doDelete(null, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-delete(), contactUs:{}", contactUs);
        return contactUs;
    }
}
