package com.rslakra.healthcare.routinecheckup.utils.components;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class InternalExceptionResolver extends AbstractHandlerExceptionResolver {

    private final Logger LOGGER = LoggerFactory.getLogger(InternalExceptionResolver.class);
    private final Messages messages;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
        if (isAnnotatedByResponseStatus(ex)) {
            return null;
        }
        LOGGER.error(ex.getMessage(), ex);

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        MappingJackson2JsonView view = new MappingJackson2JsonView();
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("message", messages.getInternalException());
        return mav;
    }

    private boolean isAnnotatedByResponseStatus(Exception ex) {
        Class<? extends Exception> aClass = ex.getClass();
        ResponseStatus annotation = aClass.getAnnotation(ResponseStatus.class);
        return annotation != null;
    }

}
