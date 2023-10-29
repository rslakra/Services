//package com.rslakra.services.automobile.aop;
//
//import com.devamatre.framework.spring.exception.DuplicateRecordException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class AutomobileControllerAdvice {
//
//    /**
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(value = DuplicateRecordException.class)
//    public ModelAndView duplicateRecordException(DuplicateRecordException exception) {
//        final ModelAndView modelAndView = new ModelAndView();
////        modelAndView.addObject("reference", e.getBook().getIsbn());
////        modelAndView.addObject("object", e.getBook());
//        modelAndView.addObject("message", "Cannot add an already existing user!");
//        modelAndView.setViewName("/register?error=true");
//        return modelAndView;
//    }
//}