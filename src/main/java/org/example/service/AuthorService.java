package org.example.service;

import org.example.model.Author;
import org.example.resource.payload.AuthorPayload;

import java.util.List;

public interface AuthorService {
    Author findAuthorId(int id);

    List<Author> getAuthorList();

    void save(AuthorPayload authorPayload);

    void remove(int id);
}
