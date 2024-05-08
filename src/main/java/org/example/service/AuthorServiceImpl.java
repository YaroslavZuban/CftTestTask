package org.example.service;

import org.example.config.HibernateConfig;
import org.example.dao.*;
import org.example.model.Author;
import org.example.resource.payload.AuthorPayload;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDto;

    public AuthorServiceImpl() {
        authorDto = new AuthorDaoImpl(HibernateConfig.getNewSessionFactory());
    }

    @Override
    public Author findAuthorId(int id) {
        return authorDto.findAuthorId(id);
    }

    @Override
    public List<Author> getAuthorList() {
        return authorDto.getAuthorList();
    }

    @Override
    public void save(AuthorPayload authorPayload) {
        authorDto.addAuthor(authorPayload);
    }

    @Override
    public void remove(int id) {
        authorDto.authorRemove(id);
    }
}
