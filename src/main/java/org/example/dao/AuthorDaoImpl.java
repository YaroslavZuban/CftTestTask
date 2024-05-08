package org.example.dao;

import org.example.model.Author;
import org.example.model.Book;
import org.example.resource.payload.AuthorPayload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private final SessionFactory sessionFactory;

    public AuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Author findAuthor(AuthorPayload authorPayload) {
        Session session = sessionFactory.openSession();

        if (authorPayload == null) {
            return null;
        }

        Query query = session.createQuery("FROM Author WHERE name = :name AND middleName = :middleName AND surname = :surname");
        query.setParameter("name", authorPayload.getName());
        query.setParameter("middleName", authorPayload.getMiddleName());
        query.setParameter("surname", authorPayload.getSurname());
        query.setMaxResults(1);

        return (Author) query.uniqueResult();
    }


    @Override
    public Author findAuthorId(int id) {
        Session session = sessionFactory.openSession();
        return session.get(Author.class, id);
    }

    @Override
    public List<Author> getAuthorList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Author");

        return query.list();
    }

    @Override
    public Author addAuthor(AuthorPayload authorPayload) {
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Author WHERE name = :name AND middleName = :middleName AND surname = :surname");
            query.setParameter("name", authorPayload.getName());
            query.setParameter("middleName", authorPayload.getMiddleName());
            query.setParameter("surname", authorPayload.getSurname());
            query.setMaxResults(1);

            Author existingAuthor = (Author) query.uniqueResult();

            if (existingAuthor == null) {
                existingAuthor = new Author(authorPayload.getName(), authorPayload.getMiddleName(), authorPayload.getSurname());
                session.save(existingAuthor);
            }

            transaction.commit();

            return existingAuthor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void authorRemove(int id) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Author author = session.get(Author.class, id);

            if (author != null) {
                session.delete(author);
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