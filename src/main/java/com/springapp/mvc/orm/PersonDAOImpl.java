package com.springapp.mvc.orm;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Person person) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(person);
        session.flush();
    }

    @Override
    public void save(PersonSession psession) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(psession);
        session.flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> list() {
        Session session = sessionFactory.openSession();
        List<Person> personList = session.createQuery("from Person").list();
        session.close();
        return personList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Person getPerson(String email) {
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(Person.class);
        cr.add(Restrictions.eq("email", email));

        List<Person> list = cr.list();

        Person person = list.isEmpty() ? null : list.get(0);
        session.close();
        return person;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PersonSession getPersonSession(PersonSession personSession) {
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(PersonSession.class);
        cr.add(Restrictions.eq("uuid", personSession.getUuid()));
//        cr.add(Restrictions.eq("person", personSession.getPerson()));

        PersonSession existing = (PersonSession) cr.uniqueResult();

        session.close();
        return existing;
    }

}