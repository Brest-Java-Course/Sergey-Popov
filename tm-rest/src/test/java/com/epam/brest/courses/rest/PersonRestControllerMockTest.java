package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.rest.DataFixture.PersonDataFixture;
import com.epam.brest.courses.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-rest-mock-test.xml"})
public class PersonRestControllerMockTest {

    private MockMvc mockMvc;

    @Resource
    private PersonRestController personRestController;

    @Autowired
    private PersonService personService;

    @Before
    public void setUp() {

        this.mockMvc = standaloneSetup(personRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @After
    public void tearDown() throws Exception {

        reset(personService);

    }

    @Test
    public void testAddPerson() throws Exception {


        Person personFromDb = PersonDataFixture.getExistPersonSame(1l);
        expect(personService.addPerson(anyObject(Person.class))).andReturn(personFromDb);
        replay(personService);

        ObjectMapper objectMapper = new ObjectMapper();
        String personJSON = objectMapper.writeValueAsString(PersonDataFixture.getNewPerson());

        this.mockMvc.perform(
                post("/rest/persons")
                .content(personJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"personId\":1,\"personFirstName\":\"MockFirstName\",\"personLastName\":\"MockLastName\",\"taskSet\":null}"));

        verify(personService);

    }

    @Test
    public void testGetPersons() throws Exception {

        expect(personService.getPersons()).andReturn(PersonDataFixture.getSamplePersonList());
        replay(personService);

        this.mockMvc.perform(
                get("/rest/persons")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"personId\":1,\"personFirstName\":\"MockFirstName1\",\"personLastName\":\"MockLastName1\",\"taskSet\":null},{\"personId\":2,\"personFirstName\":\"MockFirstName2\",\"personLastName\":\"MockLastName2\",\"taskSet\":null},{\"personId\":3,\"personFirstName\":\"MockFirstName3\",\"personLastName\":\"MockLastName3\",\"taskSet\":null}]"));

        verify(personService);

    }

    @Test
    public void testGetPersonById() throws Exception {

        expect(personService.getPersonById(1l)).andReturn(PersonDataFixture.getExistPerson(1l));
        replay(personService);

        this.mockMvc.perform(
                get("/rest/persons/1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"personId\":1,\"personFirstName\":\"MockFirstName1\",\"personLastName\":\"MockLastName1\",\"taskSet\":null}"));

        verify(personService);

    }

    @Test
    public void testUpdatePerson() throws Exception {

        personService.updatePerson(anyObject(Person.class));
        replay(personService);

        ObjectMapper objectMapper = new ObjectMapper();
        Person person = PersonDataFixture.getExistPerson(1l);
        person.setPersonFirstName("RestMockTestUpdated");
        String personJSON = objectMapper.writeValueAsString(person);

        ResultActions result = this.mockMvc.perform(
                put("/rest/persons")
                        .content(personJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print());
        result.andExpect(status().isOk());

        verify(personService);

    }

    @Test
    public void testRemovePerson() throws Exception {

        personService.removePerson(1l);
        replay(personService);

        ResultActions result = this.mockMvc.perform(
                delete("/rest/persons/1")
        )
                .andDo(print());
        result.andExpect(status().isOk());

        verify(personService);

    }

}