package com.volkruss.BookOhFoo.sell.domain.model.item;

/**
 * 買取対象の基底
 */
public abstract class Item {
    protected int id; // TODO
    protected String title;
    protected int price;
    protected ItemType type;
}
