package org.example.dao;

import org.example.model.Author;
import org.example.resource.payload.AuthorPayload;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface AuthorDao {
    Author findAuthor(AuthorPayload authorPayload);

    Author findAuthorId(int id);

    List<Author> getAuthorList();

    Author addAuthor(AuthorPayload authorPayload);

    void authorRemove( int id);
}