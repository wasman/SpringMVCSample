package com.springapp.mvc.orm;

import java.util.List;

public interface PersonService {

    void save(Person p);

    @SuppressWarnings("unchecked")
    List<Person> list();
}
