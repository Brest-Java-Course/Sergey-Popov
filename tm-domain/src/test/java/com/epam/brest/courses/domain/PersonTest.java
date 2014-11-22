package com.epam.brest.courses.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    private static final Long PERSON_ID = 1l;
    private static final String PERSON_FIRST_NAME = "Petr";
    private static final String PERSON_LAST_NAME = "Petrov";

    private static final Long PERSON_ID2 = 2l;
    private static final String PERSON_FIRST_NAME2 = "Ivan";
    private static final String PERSON_LAST_NAME2 = "Ivanov";

    private Person person;
    private Person person2;

    @Test
    public void testConstructors() throws Exception {

        person = new Person();
        assertNull(person.getPersonId());
        assertNull(person.getPersonFirstName());
        assertNull(person.getPersonLastName());

        person = new Person(PERSON_FIRST_NAME, PERSON_LAST_NAME);
        assertNull(person.getPersonId());
        assertNotNull(person.getPersonFirstName());
        assertNotNull(person.getPersonLastName());
        assertEquals(null, person.getPersonId());
        assertEquals(PERSON_FIRST_NAME, person.getPersonFirstName());
        assertEquals(PERSON_LAST_NAME, person.getPersonLastName());

        person = new Person(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME);
        assertNotNull(person.getPersonId());
        assertNotNull(person.getPersonFirstName());
        assertNotNull(person.getPersonLastName());
        assertEquals(PERSON_ID, person.getPersonId());
        assertEquals(PERSON_FIRST_NAME, person.getPersonFirstName());
        assertEquals(PERSON_LAST_NAME, person.getPersonLastName());

    }

    @Test
    public void testGettersSetters() throws Exception {

        person = new Person();
        person.setPersonId(PERSON_ID);
        person.setPersonFirstName(PERSON_FIRST_NAME);
        person.setPersonLastName(PERSON_LAST_NAME);

        assertNotNull(person.getPersonId());
        assertNotNull(person.getPersonFirstName());
        assertNotNull(person.getPersonLastName());
        assertEquals(PERSON_ID, person.getPersonId());
        assertEquals(PERSON_FIRST_NAME, person.getPersonFirstName());
        assertEquals(PERSON_LAST_NAME, person.getPersonLastName());

    }

    @Test
    public void testEquals() throws Exception {

        person = new Person(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME);
        person2 = new Person(PERSON_ID2, PERSON_FIRST_NAME2, PERSON_LAST_NAME2);

        assertNotNull(person);
        assertNotNull(person2);
        assertFalse(person.equals(person2));

    }

    @Test
    public void testEqualsSame() throws Exception {

        person = new Person(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME);

        assertNotNull(person);
        assertTrue(person.equals(person));

    }

    @Test
    public void testHashCode() throws Exception {

        person = new Person(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME);
        person2 = new Person(PERSON_ID2, PERSON_FIRST_NAME2, PERSON_LAST_NAME2);

        assertNotNull(person);
        assertNotNull(person2);
        assertNotEquals(person.hashCode(), person2.hashCode());

    }

    @Test
    public void testHashCodeSame() throws Exception {

        person = new Person(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME);

        assertNotNull(person);
        assertEquals(person.hashCode(), person.hashCode());

    }

    @Test
    public void testToString() throws Exception {

        person = new Person(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME);

        assertNotNull(person);
        assertEquals("Person{personId=1, personFirstName='Petr', personLastName='Petrov'}", person.toString());

    }
}