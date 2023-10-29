package com.rslakra.melody.ews.contact.payload;

import com.rslakra.melody.ews.account.payload.dto.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 4:56 PM
 */

@Getter
@Setter
@NoArgsConstructor
public class ContactUs extends Person {

    private String phone;
    private String query;

}
