package com.volkruss.BookOhFoo.sell.controller;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class RegisterForm {
    // 依頼者名
    @NotBlank
    @Length(min = 1,max = 10)
    String name;
    // 電話番号
    int phoneNumber;
    // 金額受取方法
    String receiveWay;
    // 品目明細
    @NotNull
    List<ItemDetail> detail;

    // ReceiveWay型に変換して返します
    public ReceiveWay toReceiveWay(){
        try {
            return ReceiveWay.valueOf(receiveWay);
        } catch (Exception e) {
            // TODO エラー処理
            throw new RuntimeException();
        }
    }
}
