package com.volkruss.BookOhFoo.system.error;

import org.springframework.validation.ObjectError;

public class MyError extends ObjectError {
    public MyError(String objectName, String defaultMessage) {
        super(objectName, defaultMessage);
    }
}
