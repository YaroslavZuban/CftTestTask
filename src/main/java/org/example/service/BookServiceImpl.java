package org.example.service;

import org.example.config.HibernateConfig;
import org.example.dao.BookDao;
import org.example.dao.BookDaoImpl;
import org.example.dao.ReaderDao;
import org.example.dao.ReaderDaoImpl;
import org.example.model.Book;
import org.example.resource.payload.BookPayload;
import org.example.resource.payload.Report;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl() {
        bookDao = new BookDaoImpl(HibernateConfig.getNewSessionFactory());
    }

    @Override
    public Book findBookId(int id) {
        return bookDao.findBookId(id);
    }

    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public void save(BookPayload bookPayload) {
        bookDao.addBook(bookPayload);
    }

    @Override
    public void remove(int id) {
        bookDao.bookRemove(id);
    }

}
