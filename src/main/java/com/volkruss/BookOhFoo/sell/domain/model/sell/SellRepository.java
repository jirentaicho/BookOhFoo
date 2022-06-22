package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.sell.controller.ItemDetail;

import java.util.List;

public interface SellRepository {

    // TODO delete
    List<Detail> getDetails(List<ItemDetail> details);

    void storeHead(Sell sell);

    // TODO 引数の除去
    void storeDetail(SellId sellId,List<Detail> details);

    // ワークテーブル(ヘッダ)に登録します
    void storeWorkHead(Sell sell);

    // ワークテーブル(明細)に登録します
    void storeWorkDetail(SellId sellId,List<Detail> details);

    Sell findSellBySellId(SellId sellId);

    List<Sell> findAll();

}
