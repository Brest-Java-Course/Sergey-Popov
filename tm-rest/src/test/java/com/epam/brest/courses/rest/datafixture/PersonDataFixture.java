package com.epam.brest.courses.rest.datafixture;

import com.epam.brest.courses.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 21.11.14. At 14.42
 */
public class PersonDataFixture {

    /**
     *
     */
    private static final String MOCK_FIRST_NAME = "MockFirstName";

    /**
     *
     */
    private static final String MOCK_LAST_NAME = "MockLastName";

    /**
     *
     * @return person
     */
    public static Person getNewPerson() {

        Person person = new Person();
        person.setPersonFirstName(MOCK_FIRST_NAME);
        person.setPersonLastName(MOCK_LAST_NAME);

        return person;

    }

    /**
     *
     * @param personId personId
     * @return person
     */
    public static Person getNewPerson(final Long personId) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName(MOCK_FIRST_NAME + personId);
        person.setPersonLastName(MOCK_LAST_NAME + personId);

        return person;
    }

    /**
     *
     * @param personId personId
     * @return person
     */
    public static Person getExistPersonSame(final Long personId) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName(MOCK_FIRST_NAME);
        person.setPersonLastName(MOCK_LAST_NAME);

        return person;

    }

    /**
     *
     * @param personId personId
     * @return person
     */
    public static Person getExistPerson(final Long personId) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName(MOCK_FIRST_NAME + personId);
        person.setPersonLastName(MOCK_LAST_NAME + personId);

        return person;

    }

    /**
     *
     * @return list
     */
    public static List<Person> getSamplePersonList() {
        List<Person> list = new ArrayList<>(3);
        list.add(PersonDataFixture.getNewPerson(1L));
        list.add(PersonDataFixture.getNewPerson(2L));
        list.add(PersonDataFixture.getNewPerson(3L));
        return list;
    }

}