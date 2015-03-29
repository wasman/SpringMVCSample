package com.springapp.mvc.orm;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService {

    void save(Person p);

    void save(PersonSession psession);

    @SuppressWarnings("unchecked")
    List<Person> list();

    @Transactional(readOnly = true)
    Person getPerson(String email);
}
