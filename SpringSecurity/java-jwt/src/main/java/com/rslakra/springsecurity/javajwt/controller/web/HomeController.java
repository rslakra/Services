package com.rslakra.springsecurity.javajwt.controller.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.rslakra.springsecurity.javajwt.utils.JWTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index"}, method = GET)
    public String indexPage(Model model, HttpServletRequest servletRequest) {
        model.addAttribute("requestUrl", JWTUtils.getRequestUrl(servletRequest));
        return "index";
    }

}
