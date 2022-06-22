package com.volkruss.BookOhFoo.sell.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Table(name="numbering")
public class NumberingEntity {
    @Id
    private int id;

    @Column(name="index")
    private int index;

}
