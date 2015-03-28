package com.springapp.mvc.orm;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

public class PersonDAOImpl implements PersonDAO {

    private final SessionFactory sessionFactory;

    public PersonDAOImpl(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    @Override
    public void save(Person p) {
        Session session = sessionFactory.openSession();
        if (getPerson(p.getEmail()) == null) {
            session.save(p);
        }
        else {
            session.merge(p);
        }
        Set<PersonSessions> personSessions = p.getPersonSessions();
        for (PersonSessions psession : personSessions) {
            psession.setPerson(p);
            session.save(psession);
        }

        session.flush();
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

}