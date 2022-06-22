package com.volkruss.BookOhFoo.book.infrastructure.persistence.map.book;

import com.volkruss.BookOhFoo.book.domain.model.book.BookId;
import com.volkruss.BookOhFoo.book.domain.model.book.Price;
import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import com.volkruss.BookOhFoo.book.infrastructure.model.book.BookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("BookRepositoryMap")
public class BookRepositoryMap implements BookRepository {

    private BookTable bookTable = BookTable.getInstance();

    @Override
    public Book findById(BookId bookId) {
        return this.toBook(this.bookTable.getTableManager().findById(bookId.getId()));
    }

    @Override
    public List<Book> findByBookIdIn(List<String> bookIds) {
        return this.bookTable.getTableManager().getTableOfList().stream()
                .filter(i -> bookIds.contains(i.getBookId()))
                .map(this::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return this.bookTable.getTableManager().getTableOfList().stream()
                .map(this::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public void store(Book book) {
        BookEntity entity = new BookEntity(
                0,
                book.getBookId().getId(),
                book.getPrice().getValue(),
                book.getTitle()
        );
        this.bookTable.getTableManager().add(entity.getBookId(),entity);
    }

    @Override
    public void update(Book book) {
        BookEntity entity = new BookEntity(0,book.getBookId().getId(),book.getPrice().getValue(),book.getTitle());
        this.bookTable.getTableManager().add(entity.getBookId(),entity);
    }

    @Override
    public void delete(Book book) {
        this.bookTable.getTableManager().remove(book.getBookIdStr());
    }

    private Book toBook(BookEntity entity){
        Book book = new Book(entity.getBookId(),entity.getPrice(),entity.getTitle());
        return book;
    }
}
