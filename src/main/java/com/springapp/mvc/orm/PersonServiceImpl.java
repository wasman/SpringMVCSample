package com.springapp.mvc.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonDAO dao;

    @Autowired
    public PersonServiceImpl(PersonDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(Person person) {
        Set<PersonSession> servicePersonSessions = new HashSet<>();
        Set<PersonSession> personSessions = person.getPersonSessions();
        Person servicePerson = dao.getPerson(person.getEmail());
        if (servicePerson == null) {
            servicePerson = person;
            System.out.println("# Using new person and new Session");
        }
        else {
            System.out.println("# Using existing person");
            servicePerson.setBirthDate(person.getBirthDate());
            servicePerson.setCountry(person.getCountry());
            servicePerson.setName(person.getName());
            for (PersonSession session : personSessions) {
                PersonSession localSession = dao.getPersonSession(session);
                if (localSession == null) {
                    localSession = session;
                    System.out.println("# Using new session");
                }
                else {
                    System.out.println("# Using existing session");
                }
                servicePersonSessions.add(localSession);
            }
        }

        System.out.println("#$ saving " + servicePersonSessions);
        System.out.println("#$ saving " + servicePerson);

        dao.save(servicePerson);
    }

    @Override
    public void save(PersonSession psession) {
//        Person person = psession.getPerson();
        Person person = new Person();
        PersonSession serviceSession;
        Person servicePerson = dao.getPerson(person.getEmail());
        if (servicePerson == null) {
            servicePerson = person;
            System.out.println("# Using new person and new Session");
            serviceSession = psession;
        }
        else {
            System.out.println("# Using existing person");
            servicePerson.setBirthDate(person.getBirthDate());
            servicePerson.setCountry(person.getCountry());
            servicePerson.setName(person.getName());

//            psession.setPerson(servicePerson);

            serviceSession = dao.getPersonSession(psession);
            if (serviceSession == null) {
                serviceSession = psession;
                System.out.println("# Using new session");
            }
            else {
                System.out.println("# Using existing session");
            }
        }

//        serviceSession.setPerson(servicePerson);
        System.out.println("#$ saving " + serviceSession);
        System.out.println("#$ saving " + servicePerson);
        dao.save(serviceSession);
    }

    @Override
    public List<Person> list() {
        return dao.list();
    }

    @Override
    public Person getPerson(String email) {
        return dao.getPerson(email);

    }
}
