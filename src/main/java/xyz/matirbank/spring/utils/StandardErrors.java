package xyz.matirbank.spring.utils;

import xyz.matirbank.spring.models.responses.base.ErrorResponse;

public class StandardErrors {
    public static ErrorResponse E1001_USER_ACCOUNT_ALREADY_EXISTS = new ErrorResponse(1001, "User Account Already Exists");
    public static ErrorResponse E1002_PHONE_NUMBER_ALREADY_EXISTS = new ErrorResponse(1002, "Phone Number Already Exists");
}
