package com.epam.brest.courses.servicerest.impl;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.servicerest.PersonServiceRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-rest-test.xml"})
@TestExecutionListeners(
        listeners = {
                DependencyInjectionTestExecutionListener.class,
                DirtiesContextTestExecutionListener.class
        }
)
public class PersonServiceRestImplTest {

    @Autowired
    private PersonServiceRest personServiceRest;

    /**
     * AddPerson test
     * @throws Exception
     */
    @Test
    public void testAddPerson() throws Exception {

        List<Person> persons = personServiceRest.getPersons();
        int sizeBefore = persons.size();

        Person person = new Person();
        person.setPersonFirstName("testFirstName");
        person.setPersonLastName("testLastName");

        personServiceRest.addPerson(person);

        persons = personServiceRest.getPersons();

        assertEquals(sizeBefore, persons.size() - 1);

    }

    /**
     * getPersons test
     * @throws Exception
     */
    @Test
    public void testGetPersons() throws Exception {

        List<Person> persons = personServiceRest.getPersons();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

    }

    /**
     * getPersonById test
     * @throws Exception
     */
    @Test
    public void testGetPersonById() throws Exception {

        Person testPersonAdd = new Person();
        testPersonAdd.setPersonFirstName("testGetPersonByIdF");
        testPersonAdd.setPersonLastName("testGetPersonByIdL");

        Long id = personServiceRest.addPerson(testPersonAdd).getPersonId();

        Person testPersonGet = personServiceRest.getPersonById(id);

        assertNotNull(testPersonGet);
        assertNotNull(testPersonGet.getPersonId());
        assertNotNull(testPersonGet.getPersonFirstName());
        assertNotNull(testPersonGet.getPersonLastName());
        assertEquals(testPersonAdd.getPersonFirstName(), testPersonGet.getPersonFirstName());
        assertEquals(testPersonAdd.getPersonLastName(), testPersonGet.getPersonLastName());

    }

    /**
     * updatePerson test
     * @throws Exception
     */
    @Test
    public void testUpdatePerson() throws Exception {

        Person testPersonAdd = new Person();
        testPersonAdd.setPersonFirstName("testUpdatePersonF");
        testPersonAdd.setPersonLastName("testUpdatePersonL");

        Long id = personServiceRest.addPerson(testPersonAdd).getPersonId();

        Person testPersonUpdate = new Person();
        testPersonUpdate.setPersonId(id);
        testPersonUpdate.setPersonFirstName("testUpdatePersonFUpdated");
        testPersonUpdate.setPersonLastName("testUpdatePersonLUpdated");

        personServiceRest.updatePerson(testPersonUpdate);

        Person testPersonGet = personServiceRest.getPersonById(id);

        assertNotNull(testPersonGet);
        assertNotNull(testPersonGet.getPersonId());
        assertNotNull(testPersonGet.getPersonFirstName());
        assertNotNull(testPersonGet.getPersonLastName());
        assertEquals(testPersonUpdate.getPersonId(), testPersonGet.getPersonId());
        assertEquals(testPersonUpdate.getPersonFirstName(), testPersonGet.getPersonFirstName());
        assertEquals(testPersonUpdate.getPersonLastName(), testPersonGet.getPersonLastName());

    }

    /**
     * removePerson test
     * @throws Exception
     */
    @Test
    public void testRemovePerson() throws Exception {

        List<Person> persons = personServiceRest.getPersons();
        int sizeBefore = persons.size();

        personServiceRest.removePerson(persons.get(persons.size() - 1).getPersonId());

        persons = personServiceRest.getPersons();
        int sizeAfter = persons.size();

        assertEquals(sizeBefore - 1, sizeAfter);

    }

}