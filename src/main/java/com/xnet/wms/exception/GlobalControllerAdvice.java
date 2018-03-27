/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.exception;

import com.xnet.wms.entity.SystemException;
import com.xnet.wms.service.SystemExceptionService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author ramy
 */
@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "/error";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @Autowired
    SystemExceptionService exceptionService;
//
    @ExceptionHandler(value = HibernateJdbcException.class)
    public ModelAndView hibernateJdbcException(HttpServletRequest request, Exception e) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
//ipAddress = request.getRemoteAddr();      
        exceptionService.addNew(new SystemException(e.getMessage(), request.getRequestURI()));
        LOGGER.error("error>>>>", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//
//        ApiError apiError
//                = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestParameter(
//            MissingServletRequestParameterException ex, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//        String error = ex.getParameterName() + " parameter is missing";
//
//        ApiError apiError
//                = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
//        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<Object> handleConstraintViolation(
//            ConstraintViolationException ex, WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " "
//                    + violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//
//        ApiError apiError
//                = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//
//    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
//            MethodArgumentTypeMismatchException ex, WebRequest request) {
//        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
//
//        ApiError apiError
//                = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
//        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
}
