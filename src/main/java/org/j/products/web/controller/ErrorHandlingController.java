package org.j.products.web.controller;

import org.j.products.web.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Locale;

/**
 * Created by Andrew on 2/25/17.
 */

@ControllerAdvice
public class ErrorHandlingController {
    @Autowired
    MessageSource messageSource;

    @ExceptionHandler({ NotFoundException.class, NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(final Exception e, final Locale locale) {
        final ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMsg", messageSource.getMessage("error.not_found", new Object[0], locale));
        return mv;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ModelAndView handleError(final Exception e, final Locale locale) {
        final ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMsg", messageSource.getMessage("error.internal_error", new Object[0], locale));
        return mv;
    }
}
