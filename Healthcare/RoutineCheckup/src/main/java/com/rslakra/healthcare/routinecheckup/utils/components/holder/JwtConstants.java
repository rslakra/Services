package com.rslakra.healthcare.routinecheckup.utils.components.holder;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:44 PM
 */
public interface JwtConstants {

    String getJwtKey();

    Long getExpirationTimeMs();

    String getLoginFieldName();

    String getParameterName();

}
