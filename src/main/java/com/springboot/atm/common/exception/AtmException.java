package com.springboot.atm.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AtmException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
