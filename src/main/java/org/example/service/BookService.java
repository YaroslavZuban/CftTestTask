package org.example.service;

import org.example.model.Author;
import org.example.model.Book;
import org.example.resource.payload.AuthorPayload;
import org.example.resource.payload.BookPayload;
import org.example.resource.payload.Report;

import java.util.List;

public interface BookService {
    Book findBookId(int id);

    List<Book> getBookList();

    void save(BookPayload bookPayload);

    void remove(int id);
}
