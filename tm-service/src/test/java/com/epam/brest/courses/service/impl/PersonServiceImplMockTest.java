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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-mock-test.xml"})
@TestExecutionListeners(
        listeners = {
                DependencyInjectionTestExecutionListener.class,
                DirtiesContextTestExecutionListener.class,
                TransactionalTestExecutionListener.class,
                SqlScriptsTestExecutionListener.class
        }
)
public class PersonServiceImplMockTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonDao personDaoMock;

    @After
    public void tearDown() throws Exception {

        reset(personDaoMock);

    }

    /**
     * AddPerson test
     * @throws Exception
     */
    @Test
    public void testAddPerson() throws Exception {

        Person person = PersonDataFixture.getNewPerson();
        Person person2 = PersonDataFixture.getExistPersonSame(1l);

        expect(personDaoMock.addPerson(person)).andReturn(person2);
        replay(personDaoMock);

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

        expect(personDaoMock.getPersons()).andReturn(PersonDataFixture.getSamplePersonList());
        replay(personDaoMock);

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
        expect(personDaoMock.getPersonById(person.getPersonId())).andReturn(person);
        replay(personDaoMock);

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

        personDaoMock.updatePerson(anyObject(Person.class));
        replay(personDaoMock);
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

        personDaoMock.removePerson(1l);
        replay(personDaoMock);
        personService.removePerson(1l);

    }

}