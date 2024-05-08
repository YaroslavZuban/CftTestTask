package org.example.dao;

import org.example.model.Gender;
import org.example.model.Reader;
import org.hibernate.Session;

public interface GenderDao {
    Gender findGenderId(Session session,int id);
}
