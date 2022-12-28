package com.rslakra.springbootsamples.springbootsample.service.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 1/6/22 5:26 PM
 */
@Getter
@Setter
@NoArgsConstructor
public final class MailModel extends ConcurrentHashMap<String, Object> {

}
