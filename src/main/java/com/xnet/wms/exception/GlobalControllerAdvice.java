/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.exception;



import com.xnet.wms.entity.SystemException;
import com.xnet.wms.service.SystemExceptionService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateJdbcException;
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

    @Autowired
    SystemExceptionService exceptionService;
    @ExceptionHandler(value=HibernateJdbcException.class)
     public ModelAndView hibernateJdbcException(HttpServletRequest request, Exception e) {
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        
        exceptionService.addNew(new SystemException(e.getMessage(), request.getPathInfo() ));
        LOGGER. error("error>>>>", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
