package com.springapp.mvc.orm;

import java.util.List;

public interface PersonDAO {

    void save(Person p);

    void save(PersonSession psession);

    @SuppressWarnings("unchecked")
    List<Person> list();

    @SuppressWarnings("unchecked")
    Person getPerson(String email);

    @SuppressWarnings("unchecked")
    PersonSession getPersonSession(PersonSession personSession);
}
