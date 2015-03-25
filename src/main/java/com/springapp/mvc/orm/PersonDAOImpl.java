package com.springapp.mvc.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    private final SessionFactory sessionFactory;

    public PersonDAOImpl(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    @Override
    public void save(Person p) {
        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        session.persist(p);
//        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> list() {
        Session session = sessionFactory.openSession();
        List<Person> personList = session.createQuery("from Person").list();
        session.close();
        return personList;
    }

}