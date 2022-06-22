package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;
import lombok.Getter;

import java.util.List;

@Getter
// @Setter
public class Sell {
    private String sellerName;

    private List<Detail> details;

    private int phoneNumber;

    private Price totalAmt = new Price(0);

    private Price campaign = new Price(0);

    private String way;

    private SellId sellId;

    public Sell(String name,int phone,String way,String sellId,int amt,int campaign){
        this.sellerName = name;
        this.phoneNumber = phone;
        this.way = way;
        this.sellId = new SellId(sellId);
        this.totalAmt = new Price(amt);
        this.campaign = new Price(campaign);
    }

    //　明細情報を登録する
    public void registerDetail(List<Detail> detail){
        this.details = detail;
    }

    // キャンペーンを適用させます
    public void applyCampaign(Campaign campaign){
        Price campaignPrice = campaign.apply(this);
        this.campaign = campaignPrice;
    }

    public int getAmount(){
        return this.totalAmt.getValue();
    }

    public void addAmt(int amt){
        this.totalAmt = this.totalAmt.add(new Price(amt));
    }

    // TODO delete methods
    public void subAmt(int amt){
        this.totalAmt = this.totalAmt.sub(new Price(amt));
    }

    public int getCampaignAmt(){
        return this.campaign.getValue();
    }

    public int calcQuantity(){
        return details.stream().reduce(
                0,
                (ac,detail) -> ac + detail.getCount(),
                (ac1,ac2) -> ac1 + ac2);
    }

}
