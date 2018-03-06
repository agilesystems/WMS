/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms;



import javax.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ramy
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private static final String DEFAULT_ERROR_VIEW = "/error";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

  

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        LOGGER. error("error>>>>", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
