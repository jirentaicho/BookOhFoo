package com.volkruss.BookOhFoo.sell.infrastructure.model.sell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="sellheaderwork")
public class SellHeaderEntity {
    @Id
    private int id;

    @Column(name="sell_id")
    private String sellId;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private int phone;

    @Column(name="total_amt")
    private int totalAmt;

    @Column(name="discount")
    private int discount;

    @Column(name="way")
    private String way;

    @Column(name="created_at")
    private String createdAt;
}
