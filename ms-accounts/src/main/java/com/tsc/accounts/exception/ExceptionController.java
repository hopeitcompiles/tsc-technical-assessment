package com.tsc.accounts.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    private HashMap<String,Object> buildDefaultExceptionMap(HttpStatus status) {
        return new HashMap<>(){
            {
                put("status", status);
                put("status_code", status.value());
                put("timestamp", LocalDateTime.now());
            }};
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> entityNotFound(EntityNotFoundException ex){
        Map<String,Object> map= buildDefaultExceptionMap(HttpStatus.NOT_FOUND);
        map.put("message",ex.getMessage());
        return map;
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,Object> entityAlreadyExists(EntityExistsException ex){
        Map<String,Object> map= buildDefaultExceptionMap(HttpStatus.CONFLICT);
        map.put("message",ex.getMessage());
        return map;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> bodyNotReadable(HttpMessageNotReadableException ex){
        Map<String,Object> map=buildDefaultExceptionMap(HttpStatus.BAD_REQUEST);
        map.put("message",ex.getMessage());
        return map;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> noResourceFound(NoResourceFoundException ex){
        Map<String,Object> map=buildDefaultExceptionMap(HttpStatus.NOT_FOUND);
        map.put("message",ex.getMessage());
        return map;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> businessException(BusinessException ex){
        Map<String,Object> map=buildDefaultExceptionMap(HttpStatus.BAD_REQUEST);
        map.put("message",ex.getMessage());
        return map;
    }



    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> internalServerError(RuntimeException ex){
        logger.error("Internal Server Error: "+ex);
        Map<String,Object> map=buildDefaultExceptionMap(HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("message","Internal Server Error");
        return map;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> validationException(MethodArgumentNotValidException ex){
        logger.error("Invalid arguments: "+ex.toString());
        Map<String,Object> map=buildDefaultExceptionMap(HttpStatus.BAD_REQUEST);
        map.put("message",ex.getBindingResult().getFieldErrors().stream().map(arg->arg.getField()+":"+arg.getDefaultMessage() ).collect(Collectors.joining(", ")));
        map.put("errors",ex.getBindingResult().getFieldErrors().stream().map(arg->arg.getField()+":"+arg.getDefaultMessage() ).collect(Collectors.toSet()));
        return map;
    }

}
