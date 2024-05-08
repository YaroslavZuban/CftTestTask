package org.example.dao;

import org.example.model.Book;
import org.example.resource.payload.BookPayload;

import java.util.List;

public interface BookDao {
    Book findBookId(int id);
    List<Book> getBookList();

    Book addBook(BookPayload book);

    void bookRemove(int id);
}
