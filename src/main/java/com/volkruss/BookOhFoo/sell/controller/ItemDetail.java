package com.volkruss.BookOhFoo.sell.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// 品物明細です
@Setter
@Getter
public class ItemDetail {
    // 商品ID
    @NotNull
    String itemId;
    @NotNull
    @Min(1)
    // 数量
    int count;
    // 明細番号
    int detailNo;
}
