package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.service.XPathComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:21 PM
 */
@Component
public class XPathComponentImpl implements XPathComponent {

    private final Logger logger
        = LoggerFactory.getLogger(XPathComponentImpl.class);

    @Override
    public NodeList evaluateXpathExpression(
        String expression,
        String fullFileName
    ) throws FileNotFoundException {

        Document document;
        try {
            document = getDocument(fullFileName);
        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        XPath xPath = XPathFactory
            .newInstance()
            .newXPath();
        NodeList nodeList;
        try {
            nodeList = (NodeList) xPath
                .compile(expression)
                .evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        return nodeList;
    }

    @Override
    public String sanitizeExpressionParam(String param) {
        String result = param
            .replace("'", "\\'")
            .replace("\"", "\\\"");
        return result;
    }

    private Document getDocument(
        String fullFileName
    ) throws
      ParserConfigurationException,
      SAXException,
      IOException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder();

        FileInputStream fileInputStream = new FileInputStream(fullFileName);
        Document document = documentBuilder.parse(fileInputStream);
        return document;
    }

}

