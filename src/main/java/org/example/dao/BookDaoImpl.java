package org.example.dao;

import org.example.config.HibernateConfig;
import org.example.model.Author;
import org.example.model.Book;
import org.example.resource.payload.AuthorPayload;
import org.example.resource.payload.BookPayload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class BookDaoImpl implements BookDao {
    private SessionFactory sessionFactory;

    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book findBookId(int id) {
        Session session = sessionFactory.openSession();
        return session.get(Book.class, id);
    }

    @Override
    public List<Book> getBookList() {
        List<Book> books = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Book");

            books = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            //ошибка
        }
        return books;
    }

    @Override
    public Book addBook(BookPayload bookPayload) {
        if (bookPayload == null) {
            return null;
        }

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Book WHERE title = :title AND " +
                    "publicationYear = :publicationYear AND " +
                    "count = :count");
            query.setParameter("title", bookPayload.getTitle());
            query.setParameter("publicationYear", bookPayload.getPublicationYear());
            query.setParameter("count", bookPayload.getCount());
            query.setMaxResults(1);

            System.out.println("Point 1");

            Book book = (Book) query.uniqueResult();

            System.out.println("Point 2");

            if (book == null) {
                System.out.println("Point 2.1");
                book = new Book(bookPayload.getTitle(), bookPayload.getPublicationYear(), bookPayload.getCount());

                System.out.println("Point 3");
                saveAuthor(book, bookPayload.getAuthorPayloads());

                session.save(book);
            } else {
                System.out.println("Point 2.3");
                int bookCount = book.getCount() + bookPayload.getCount();

                System.out.println("Point 4");

                book.setCount(bookCount);
                System.out.println("Point 4.1");

                System.out.println("Point 5");

                session.update(book);
            }

            System.out.println("Point 10");

            transaction.commit();

            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        return null;
    }

    private void saveAuthor(Book book, List<AuthorPayload> authorPayloads) {
        AuthorDao authorDao = new AuthorDaoImpl(HibernateConfig.getNewSessionFactory());

        System.out.println("Point 6");

        for (AuthorPayload authorPayload : authorPayloads) {
            System.out.println("Point 7");

            Author author = authorDao.findAuthor(authorPayload);
            System.out.println(author);

            System.out.println("Point 8");
            if (author == null) {
                author = authorDao.addAuthor(authorPayload);
            }

            System.out.println("Point 9");
            System.out.println(author);

            book.addAuthors(author);
            author.addBook(book);

            System.out.println("Point 10");
        }
    }

    @Override
    public void bookRemove(int id) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);

            if (book != null) {
                session.delete(book);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}