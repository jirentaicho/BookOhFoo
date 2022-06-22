package com.volkruss.BookOhFoo.sell.infrastructure.model.sell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="selldetail")
public class SellDetailEntity {
    @Id
    private int id;

    @Column(name="selll_id")
    private String sellId;

    @Column(name="detail_no")
    private int detailNo;

    @Column(name="book_id")
    private String bookId;

    @Column(name="count")
    private int count;

}
