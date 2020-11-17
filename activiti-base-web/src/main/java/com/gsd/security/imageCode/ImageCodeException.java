package com.gsd.security.imageCode;

import org.springframework.security.authentication.AuthenticationServiceException;

public class ImageCodeException extends AuthenticationServiceException {
    public ImageCodeException(String msg) {
        super(msg);
    }
}
