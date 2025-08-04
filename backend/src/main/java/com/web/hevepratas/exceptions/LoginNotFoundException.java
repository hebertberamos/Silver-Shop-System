package com.web.hevepratas.exceptions;

public class LoginNotFoundException extends RuntimeException {
    public LoginNotFoundException(String msg) {
        super(msg);
    }
}
