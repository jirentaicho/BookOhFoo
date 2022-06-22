package com.volkruss.BookOhFoo.sell.domain.model.numbering;

// TODO 採番という概念自体をパッケージ移動させる
public class Number {
    private int index;

    public Number(int index){
        this.index = index;
    }

    public Number getNumber(){
        this.index++;
        return new Number(this.index);
    }

    public int getCurrentIndex(){
        return this.index;
    }
}
