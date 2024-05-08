package org.example.dao;

import org.example.model.Author;
import org.example.model.Gender;
import org.example.model.Reader;
import org.example.resource.payload.ReaderPayload;
import org.example.resource.payload.Report;
import org.example.resource.payload.ReportReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ReaderDaoImpl implements ReaderDao {
    private final SessionFactory sessionFactory;

    public ReaderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Reader findReader(ReaderPayload readerPayload) {
        Session session = sessionFactory.openSession();

        if (readerPayload == null) {
            return null;
        }

        Query query = session.createQuery("FROM Author WHERE name = :name AND middleName = :middleName AND surname = :surname");
        query.setParameter("name", readerPayload.getName());
        query.setParameter("middleName", readerPayload.getMiddleName());
        query.setParameter("surname", readerPayload.getSurname());
        query.setMaxResults(1);

        return (Reader) query.uniqueResult();

    }

    @Override
    public Reader findReaderId(int id) {
        Session session = sessionFactory.openSession();
        return session.get(Reader.class, id);
    }

    @Override
    public List<Reader> getReaderList() {
        List<Reader> readers = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Reader");

            readers = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }


        }

        return readers;
    }

    @Override
    public Reader addReader(ReaderPayload readerPayload) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Author WHERE name = :name AND middleName = :middleName AND surname = :surname");
            query.setParameter("name", readerPayload.getName());
            query.setParameter("middleName", readerPayload.getMiddleName());
            query.setParameter("surname", readerPayload.getSurname());
            query.setMaxResults(1);

            Reader reader = (Reader) query.uniqueResult();

            if (reader == null) {
                GenderDao genderDao = new GenderDaoImpl();
                Gender gender = genderDao.findGenderId(session, readerPayload.getGenderId());

                if (gender == null) {
                    return null;
                }

                reader = new Reader(readerPayload.getName(), readerPayload.getMiddleName(),
                        readerPayload.getSurname(), gender);

                gender.addReader(reader);

                session.save(gender);
                session.save(reader);
            }

            transaction.commit();

            return reader;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void readerRemove(int id) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Reader reader = session.get(Reader.class, id);

            if (reader != null) {
                session.delete(reader);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    @Override
    public ReportReader repostBookCount(Report report) {
        if (report == null) {
            return null;
        }

        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("SELECT count(*) FROM authors " +
                "LEFT JOIN issuance ON authors.id = issuance.readers_id " +
                "WHERE issuance.receipt_date BETWEEN :startDate AND :endDate AND authors.id=:readerId");

        query.setParameter("readerId", report.getReaderId());
        query.setParameter("startDate", report.getStartDate());
        query.setParameter("endDate", report.getEndDate());

        Long count = (Long) query.getSingleResult();

        ReportReader reader = new ReportReader(report.getReaderId());

        if (count == null) {
            reader.setCount(0);
        } else {
            reader.setCount(Math.toIntExact(count));
        }


        return reader;
    }
}
