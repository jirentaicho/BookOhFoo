package com.volkruss.BookOhFoo.system.exception;

import org.springframework.validation.Errors;

public class InputValidationException extends RuntimeException{

    // エラーのメッセージを管理するため参照を保持するようにする
    private Errors errors;

    public InputValidationException(Errors errors){
        super();
        this.errors = errors;
    }

    public Errors getErrors(){
        return this.errors;
    }

}
