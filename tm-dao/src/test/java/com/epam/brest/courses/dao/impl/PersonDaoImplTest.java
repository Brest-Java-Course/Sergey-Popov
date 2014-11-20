package com.epam.brest.courses.dao.impl;

import com.epam.brest.courses.dao.PersonDao;
import com.epam.brest.courses.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class PersonDaoImplTest {

    @Autowired
    private PersonDao personDao;

    /**
     * AddPerson test
     * @throws Exception
     */
    @Test
    public void testAddPerson() throws Exception {

        List<Person> persons = personDao.getPersons();
        int sizeBefore = persons.size();

        Person person = new Person();
        person.setPersonFirstName("testFirstName");
        person.setPersonLastName("testLastName");

        personDao.addPerson(person);

        persons = personDao.getPersons();

        assertEquals(sizeBefore, persons.size() - 1);

    }

    /**
     * getPersons test
     * @throws Exception
     */
    @Test
    public void testGetPersons() throws Exception {

        List<Person> persons = personDao.getPersons();

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

        Long id = personDao.addPerson(testPersonAdd).getPersonId();

        Person testPersonGet = personDao.getPersonById(id);

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

        Long id = personDao.addPerson(testPersonAdd).getPersonId();

        Person testPersonUpdate = new Person();
        testPersonUpdate.setPersonId(id);
        testPersonUpdate.setPersonFirstName("testUpdatePersonFUpdated");
        testPersonUpdate.setPersonLastName("testUpdatePersonLUpdated");

        personDao.updatePerson(testPersonUpdate);

        Person testPersonGet = personDao.getPersonById(id);

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

        List<Person> persons = personDao.getPersons();
        int sizeBefore = persons.size();

        personDao.removePerson(persons.get(persons.size() - 1).getPersonId());

        persons = personDao.getPersons();
        int sizeAfter = persons.size();

        assertEquals(sizeBefore - 1, sizeAfter);

    }

}