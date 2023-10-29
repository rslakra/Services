package com.rslakra.healthcare.routinecheckup.service;


import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:17 PM
 */
public interface XPathComponent {

    NodeList evaluateXpathExpression(String expression, String fullFileName) throws FileNotFoundException;

    String sanitizeExpressionParam(String param);

}
