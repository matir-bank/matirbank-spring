package xyz.matirbank.spring.utils;

import xyz.matirbank.spring.models.responses.base.ErrorResponse;

public class StandardErrors {

    public static ErrorResponse E1001_USER_ACCOUNT_ALREADY_EXISTS = new ErrorResponse(1001, "User Account Already Exists");
    public static ErrorResponse E1002_PHONE_NUMBER_ALREADY_EXISTS = new ErrorResponse(1002, "Phone Number Already Exists");
    public static ErrorResponse E1003_USER_ACCOUNT_NOT_FOUND = new ErrorResponse(1003, "User Account Not Found");
    public static ErrorResponse E1004_INCORRECT_LOGIN_DETAILS = new ErrorResponse(1004, "Incorrect Login Details");
    public static ErrorResponse E1005_USER_ACCOUNT_UPDATE_FAILED = new ErrorResponse(1005, "User Account Update Failed");
    public static ErrorResponse E1006_SYSTEM_USER_ACCOUNT_NOT_FOUND = new ErrorResponse(1006, "System User Account Not Found");
    public static ErrorResponse E1007_USER_AUTHENTICATION_ERROR = new ErrorResponse(1007, "User Authentication Error");
    public static ErrorResponse E1501_PHOTO_UPLOAD_FAILED = new ErrorResponse(1501, "Photo Upload Failed");
    public static ErrorResponse E2001_TRANSACTION_FAILED = new ErrorResponse(2001, "Transaction Failed");
    public static ErrorResponse E3001_USER_WALLET_ACCOUNT_ALREADY_EXISTS = new ErrorResponse(3001, "User Wallet Account Already Exists");
    public static ErrorResponse E3002_SYSTEM_ACCOUNT_ALREADY_EXISTS = new ErrorResponse(3002, "System Account Already Exists");
    public static ErrorResponse E3003_UNABLE_TO_CREATE_ACCOUNT = new ErrorResponse(3003, "System Account Already Exists");

}
