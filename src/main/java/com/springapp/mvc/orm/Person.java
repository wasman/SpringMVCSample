package com.springapp.mvc.orm;

import java.util.Date;
import java.util.Set;

public class Person {

    private int id;

    private String name;

    private String email;

    private String country;

    private Date birthDate;

    private Set<PersonSession> personSessions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<PersonSession> getPersonSessions() {
        return personSessions;
    }

    public void setPersonSessions(Set<PersonSession> personSessions) {
        this.personSessions = personSessions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", personSessions=").append(personSessions);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        if (id != person.id) {
            return false;
        }
        if (name != null ? !name.equals(person.name) : person.name != null) {
            return false;
        }
        if (email != null ? !email.equals(person.email) : person.email != null) {
            return false;
        }
        if (country != null ? !country.equals(person.country) : person.country != null) {
            return false;
        }
        if (birthDate != null ? !birthDate.equals(person.birthDate) : person.birthDate != null) {
            return false;
        }
        return !(personSessions != null ? !personSessions.equals(person.personSessions) : person.personSessions != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (personSessions != null ? personSessions.hashCode() : 0);
        return result;
    }
}