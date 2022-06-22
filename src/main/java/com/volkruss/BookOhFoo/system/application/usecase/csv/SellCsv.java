package com.volkruss.BookOhFoo.system.application.usecase.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class SellCsv {
    @JsonProperty(value = "売ID",index = 1)
    private String sellId;

    @JsonProperty(value = "名前",index = 2)
    private String name;

    @JsonProperty(value = "振込方法",index = 3)
    private String way;

    @JsonProperty(value = "キャンペーン適用金額",index = 4)
    private int campaign;

    @JsonProperty(value = "合計買取金額",index = 5)
    private int amt;

    @JsonProperty(value = "電話番号",index = 6)
    private int phoneNumber;

    // 明細情報
    @JsonProperty(value = "本ID", index = 7)
    private String bookId;

    @JsonProperty(value = "数量",index = 8)
    private int count;

}
