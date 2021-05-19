package com.springboot.atm.common.advice;

import com.springboot.atm.common.exception.AtmException;
import com.springboot.atm.common.exception.ExceptionEnum;
import com.springboot.atm.common.exception.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(AtmException.class)
    public ResponseEntity<ExceptionResult> myExceptionHandler(AtmException e) {
        ExceptionEnum exceptionEnum = e.getExceptionEnum();
        return ResponseEntity.status(exceptionEnum.getCode()).body(new ExceptionResult(exceptionEnum));
    }

}
