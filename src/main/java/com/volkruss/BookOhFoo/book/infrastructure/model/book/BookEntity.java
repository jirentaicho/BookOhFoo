package com.volkruss.BookOhFoo.book.infrastructure.model.book;

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
@Table(name="books")
public class BookEntity {
    @Id
    private int id;

    @Column(name="book_id")
    private String bookId;

    @Column(name="price")
    private int price;

    @Column(name="title")
    private String title;
}
