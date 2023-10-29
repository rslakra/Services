package com.rslakra.springbootsamples.emailservice.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 4:20 PM
 */
public interface AnalysisService {

    List<String> getAllSerializedFiles();

    void getSerializedFile(String fileName, HttpServletResponse servletResponse);

    boolean deleteSerializedFile(String fileName);
}
