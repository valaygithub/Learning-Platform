package com.itv.learningplatform.exception;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    //Will Handle Specified Exception   
     @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<?>handleConstraintViolation(Exception ex)
        {
    //getting the object of root Exception which the transcation rollback
            Throwable cause=((TransactionSystemException)ex).getRootCause();

            List<String>ValidationErrors=null;
            if(cause instanceof ConstraintViolationException)
            {
                System.out.println("exception handler");
                Set<ConstraintViolation<?>>constraintViolations=((ConstraintViolationException)cause).getConstraintViolations();
                ValidationErrors=constraintViolations.stream()
                                            .map(ConstraintViolation::getMessage)
                                            .collect(Collectors.toList());
                                        }
                                        return new ResponseEntity<>(ValidationErrors,HttpStatus.BAD_REQUEST);
                        
    }
}