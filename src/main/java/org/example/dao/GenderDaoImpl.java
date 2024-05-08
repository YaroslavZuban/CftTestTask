package org.example.dao;

import org.example.model.Author;
import org.example.model.Gender;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenderDaoImpl implements GenderDao {

    @Override
    public Gender findGenderId(Session session,int id) {
        return session.get(Gender.class, id);
    }
}
