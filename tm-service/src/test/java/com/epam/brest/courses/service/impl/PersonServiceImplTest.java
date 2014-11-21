package com.epam.brest.courses.service.impl;

import com.epam.brest.courses.dao.PersonDao;
import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.service.PersonService;
import com.epam.brest.courses.service.impl.DataFixture.PersonDataFixture;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-test.xml"})
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonDao personDao;

    @After
    public void tearDown() throws Exception {

        reset(personDao);

    }

    /**
     * AddPerson test
     * @throws Exception
     */
    @Test
    public void testAddPerson() throws Exception {

        Person person = PersonDataFixture.getNewPerson();
        Person person2 = PersonDataFixture.getExistPersonSame(1l);

        expect(personDao.addPerson(person)).andReturn(person2);
        replay(personDao);

        Person personFromDb = personService.addPerson(person);

        assertEquals(person.getPersonFirstName(), personFromDb.getPersonFirstName());
        assertEquals(person.getPersonLastName(), personFromDb.getPersonLastName());

    }

    /**
     * getPersons test
     * @throws Exception
     */
    @Test
    public void testGetPersons() throws Exception {

        expect(personDao.getPersons()).andReturn(PersonDataFixture.getSamplePersonList());
        replay(personDao);

        List<Person> persons = personService.getPersons();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

    }

    /**
     * getPersonById test
     * @throws Exception
     */
    @Test
    public void testGetPersonById() throws Exception {

        Person person = PersonDataFixture.getExistPerson(1l);
        expect(personDao.getPersonById(person.getPersonId())).andReturn(person);
        replay(personDao);

        Person personFromDb = personService.getPersonById(person.getPersonId());

        assertEquals(person.getPersonFirstName(), personFromDb.getPersonFirstName());
        assertEquals(person.getPersonLastName(), personFromDb.getPersonLastName());

    }

    /**
     * updatePerson test
     * @throws Exception
     */
    @Test
    public void testUpdatePerson() throws Exception {

        personDao.updatePerson(anyObject(Person.class));
        replay(personDao);
        Person person = PersonDataFixture.getExistPerson(1l);
        person.setPersonFirstName("TestMockUpdate");
        personService.updatePerson(person);

    }

    /**
     * removePerson test
     * @throws Exception
     */
    @Test
    public void testRemovePerson() throws Exception {

        personDao.removePerson(1l);
        replay(personDao);
        personService.removePerson(1l);

    }

}