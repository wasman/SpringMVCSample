package com.springapp.mvc.orm;

public class PersonSession {

    private int id;

    private Person person;

    private String uuid;

    public PersonSession() {
    }

    public PersonSession(String uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonSessions{");
        sb.append("id=").append(id);
//        sb.append(", person=").append(person.getEmail());
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
