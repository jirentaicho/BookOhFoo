package com.volkruss.BookOhFoo.sell.domain.model.numbering;

import java.util.Objects;

public class Numbering {

    private Number number;

    private String application;

    public Numbering(int index){
        this.number = new Number(index);
    }

    public int getNumber(){
        this.number = this.number.getNumber();
        return this.number.getCurrentIndex();
    }

    public void setApplication(String application){
        this.application = application;
    }

    public String getNumberString(){
        if(Objects.isNull(this.application)){
            // TODO アプリケーションが設定されていません。
        }
        return this.application + Integer.toString(this.number.getCurrentIndex());
    }

    @Deprecated
    public String getNumberStr(){
        this.number = this.number.getNumber();
        return this.application + Integer.toString(this.number.getCurrentIndex());
    }

}
