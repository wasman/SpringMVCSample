package com.springapp.mvc.orm;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDAOImpl dao;

    public PersonServiceImpl(PersonDAOImpl dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Person person) {
        dao.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> list() {

        return dao.list();

    }

    @Transactional(readOnly = true)
    @Override
    public Person getPerson(String email) {

        return dao.getPerson(email);

    }
}
