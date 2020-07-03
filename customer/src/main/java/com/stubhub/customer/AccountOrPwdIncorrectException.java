package com.stubhub.customer;

public class AccountOrPwdIncorrectException extends RuntimeException {

    public AccountOrPwdIncorrectException(){
        super();
    }

    public AccountOrPwdIncorrectException(String message) {
        super(message);
    }
}
