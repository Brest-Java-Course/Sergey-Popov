package com.epam.brest.courses.domain;

import java.util.Set;

/**
 * Created by beast on 17.11.14. At 15.42
 */
public class Person {

    private Long personId;

    private String personFirstName;

    private String personLastName;

    private Set<Task> taskSet;

    public Person() {

    }

    public Person(String personFirstName, String personLastName) {

        this.personFirstName = personFirstName;
        this.personLastName = personLastName;

    }

    public Person(Long personId, String personFirstName, String personLastName) {

        this.personId = personId;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;

    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
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

        return personFirstName.equals(person.personFirstName) && personId.equals(person.personId)
                && personLastName.equals(person.personLastName);

    }

    @Override
    public int hashCode() {

        int result = personId.hashCode();
        result = 31 * result + personFirstName.hashCode();
        result = 31 * result + personLastName.hashCode();
        result = 31 * result + (taskSet != null ? taskSet.hashCode() : 0);
        return result;

    }

    @Override
    public String toString() {

        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("personId=").append(personId);
        sb.append(", personFirstName='").append(personFirstName).append('\'');
        sb.append(", personLastName='").append(personLastName).append('\'');
        sb.append('}');
        return sb.toString();

    }

}