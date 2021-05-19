package com.springboot.atm.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    QUERY_BALANCE_ERROR(500, "Query balance failed, internal server error"),
    LOGIN_ERROR(500, "login failed, server error"),
    READ_CARD_ERROR(500, "card reading error"),
    UPDATE_PASSWORD_ERROR(500, "update password failed"),
    WITHDRAWAL_ERROR(500, "withdrawal failed, internal server error"),
    TRANSFER_ACCOUNT_FOUND(500, "transfer account not found"),
    TRANSFER_ACCOUNT_ERROR(500, "transfer account error, internal server error"),
    DEPOSIT_ERROR(500, "deposit error");
    private Integer code;
    private String msg;
}
