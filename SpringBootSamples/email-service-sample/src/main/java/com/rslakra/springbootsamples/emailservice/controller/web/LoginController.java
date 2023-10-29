package com.rslakra.springbootsamples.emailservice.controller.web;


import com.rslakra.springbootsamples.emailservice.Constants;
import com.rslakra.springbootsamples.emailservice.domain.user.UserInfo;
import com.rslakra.springbootsamples.emailservice.service.UserInfoService;
import com.rslakra.springbootsamples.emailservice.utils.AppUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:19 PM
 */
@Controller
public class LoginController {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserInfoService userService;

    private String username;
    private String password;
    private String username_error;
    private String password_error;
    private Map<String, String> errors;

    /**
     * Method to render the login page
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String viewLoginPage(HttpServletRequest request) {
        //logs debug payload
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("User Controller is executed!");
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            if ((Object) session.getAttribute("isValidUser") != null) {
                if ((Boolean) session.getAttribute("isValidUser") == true) {
                    if ((Boolean) session.getAttribute("isAdmin") == true) {
                        return "redirect:" + Constants.URL_OWNER_HOME;
                    } else {
                        return "redirect:" + Constants.URL_USER_HOME;
                    }
                }
            }
        }
        return Constants.URL_LOGIN;
    }

    /**
     * Method to handle the user authentication request.
     *
     * @param request
     * @param response
     * @param session
     * @param model
     * @param redir
     * @return
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticateUser(HttpServletRequest request, HttpServletResponse response,
                                   HttpSession session, Model model, RedirectAttributes redir) {
        username = request.getParameter("user_name").trim();
        password = request.getParameter("password").trim();
        errors = new HashMap<String, String>();
        boolean isAdmin = false;
        String homePage = Constants.URL_USER_HOME;
        // if fields are empty or invalid input return error
        if (!validateLoginForm()) {
            if (!username_error.trim().isEmpty()) {
                redir.addFlashAttribute("username_error", username_error);
            }
            if (!password_error.trim().isEmpty()) {
                redir.addFlashAttribute("password_error", password_error);
            }
            if (errors.size() > 0) {
                redir.addFlashAttribute("errors", errors);
            }
            return "redirect:" + Constants.URL_LOGIN;
        }

        UserInfo user = userService.getUserByName(username);

        // validate the password
        boolean isValidUser = false;
        if (user != null) {
            isValidUser = AppUtils.isValidPassword(user.getSalt(), password, user.getPassword());
        } else {
            isValidUser = AppUtils.isValidPassword("this", "wont", "match"); // beware of timing
        }

        // if password doesn't match return error
        if (!isValidUser || user == null) {
            redir.addFlashAttribute("errorMessage", Constants.MSG_BAD_LOGIN_INPUT);
            return "redirect:" + Constants.URL_LOGIN;
        }
        if (user.getRoleId() == Constants.ADMIN_ROLE_ID) {
            isAdmin = true;
            homePage = Constants.URL_OWNER_HOME;
        }
        session.setAttribute("user", user);
        session.setAttribute("isValidUser", true);
        session.setAttribute("isAdmin", isAdmin);
        session.setAttribute("username", ESAPI.encoder().encodeForHTML(user.getUserName()));

        return "redirect:" + homePage;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request, HttpServletResponse response,
                             Model model, RedirectAttributes redir) {
        HttpSession session = request.getSession();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
        if (session != null) {
            session.removeAttribute("user");
            session.removeAttribute("isValidUser");
            session.removeAttribute("username");
            session.removeAttribute("isAdmin");
        }
        session.invalidate();
        redir.addFlashAttribute("successMessage", Constants.MSG_LOGOUT_SUCCESS);
        return "redirect:" + Constants.URL_LOGIN;
    }

    @RequestMapping(value = "/invalidUser", method = RequestMethod.GET)
    public String logoutInvalidUser(HttpServletRequest request, RedirectAttributes redir) {
        redir.addFlashAttribute("errorMessage", Constants.MSG_INVALID_USER);
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("user");
            session.removeAttribute("isValidUser");
            session.removeAttribute("username");
            session.removeAttribute("isAdmin");
        }
        session.invalidate();
        return "redirect:" + Constants.URL_LOGIN;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(HttpServletRequest request) {
        return Constants.URL_ERROR;
    }

    /**
     * Validate for Empty fields
     *
     * @return
     */
    private boolean validateLoginForm() {
        boolean isValid = true;
        username_error = "";
        password_error = "";
        errors.clear();
        if (username == null || username.isEmpty()) {
            isValid = false;
            username_error = Constants.MSG_REQUIRED_USERNAME;
        }
        if (password == null || password.isEmpty()) {
            isValid = false;
            password_error = Constants.MSG_REQUIRED_PASSWORD;
        }
        if (isValid) {
            boolean isValidInput = hasValidFormInputs();
            if (!isValidInput) {
                LOGGER.warn("Invalid string is used for login.");
                isValid = false;
                errors.put("invalidUserInput", Constants.MSG_BAD_LOGIN_INPUT);
            }
        }
        return isValid;
    }

    /**
     * Validate for invalid value of text fields (xss attacks)
     *
     * @return
     */
    private boolean hasValidFormInputs() {
        boolean isValidInput = true;
        try {
            if (username.contains("@") || username.indexOf("@") != -1) {
                isValidInput = ESAPI.validator().isValidInput("User Name", username, "Email", 30, false);
            } else {
                isValidInput = ESAPI.validator().isValidInput("User Name", username, "Username", 30, false);
            }
        } catch (IntrusionException e) {
            isValidInput = false;
        }
        return isValidInput;
    }
}
