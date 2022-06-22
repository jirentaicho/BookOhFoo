package com.volkruss.BookOhFoo.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Optional;

public class EasyErrors {

    private final Errors errors;

    public EasyErrors(Errors errors){
        this.errors = errors;
    }

    /**
     * フィールド名からメッセージを取得します
     * フィールドが存在しない場合はEmptyを返します
     *
     * @param name
     * @return
     */
    public Optional<String> getMessageByFieldNameOpt(String name){
        if(this.errors.hasFieldErrors(name)){
            FieldError fe = this.errors.getFieldError(name);
            return Optional.of(fe.getDefaultMessage());
        }
        return Optional.empty();
    }

    /**
     * フィールド名からメッセージを取得します
     * フィールドが存在しない場合は例外になりますので、必ず存在チェックと併用してください
     *
     * @param name
     * @return
     */
    public String getMessageByFieldName(String name) {
        FieldError fe = this.errors.getFieldError(name);
        return fe.getDefaultMessage();
    }

    public boolean hasMessageByFieldName(String name){
        return this.errors.hasFieldErrors(name);
    }

}
