package com.example.demo1.config;


import com.example.demo1.dto.RspDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;


/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final int ARGUMENT_ERR_CODE = 1001;
    private static final int VALIDATION_ERR_CODE = 1002;
    private static final int CONSTRAINT_ERR_CODE = 1003;


    /**
     * 方法参数校验 POST @RequestBody
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RspDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(ARGUMENT_ERR_CODE, e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * 方法参数校验 Get 非 @RequestBody
     */
    @ExceptionHandler(BindException.class)
    public RspDTO validExceptionHandler(BindException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(ARGUMENT_ERR_CODE, e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * ValidationException
     * post  @RequestBody
     */
    @ExceptionHandler(ValidationException.class)
    public RspDTO handleValidationException(ValidationException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(VALIDATION_ERR_CODE, e.getCause().getMessage());
    }


    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RspDTO handleConstraintViolationException(ConstraintViolationException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(CONSTRAINT_ERR_CODE, e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public RspDTO handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(500, "系统繁忙,请稍后再试");
    }


}
