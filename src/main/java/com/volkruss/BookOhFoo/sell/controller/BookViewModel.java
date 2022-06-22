package com.volkruss.BookOhFoo.sell.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookViewModel {
    private String id;
    private String title;
    private int price;
}
