package com.epam.brest.courses.rest.DataFixture;

import com.epam.brest.courses.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 21.11.14. At 14.42
 */
public class PersonDataFixture {

    public static Person getNewPerson() {

        Person person = new Person();
        person.setPersonFirstName("MockFirstName");
        person.setPersonLastName("MockLastName");

        return person;

    }

    public static Person getNewPerson(Long personId) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName("MockFirstName" + personId);
        person.setPersonLastName("MockLastName" + personId);

        return person;
    }

    public static Person getExistPersonSame(Long personId) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName("MockFirstName");
        person.setPersonLastName("MockLastName");

        return person;

    }

    public static Person getExistPerson(Long personId) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName("MockFirstName" + personId);
        person.setPersonLastName("MockLastName" + personId);

        return person;

    }

    public static List<Person> getSamplePersonList() {
        List<Person> list = new ArrayList<>(3);
        list.add(PersonDataFixture.getNewPerson(1L));
        list.add(PersonDataFixture.getNewPerson(2L));
        list.add(PersonDataFixture.getNewPerson(3L));
        return list;
    }

}