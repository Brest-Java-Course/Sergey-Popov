package com.epam.brest.courses.web;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import com.epam.brest.courses.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 24.11.14. At 12.17
 */
@Controller
@RequestMapping("/mvc/person")
public class PersonController {

    private static final String PERSON_LIST = "redirect:/mvc/person/getPersons";

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PersonService personService;

    @RequestMapping
    public String init() {

        return PERSON_LIST;

    }

    @RequestMapping(value="/addPerson", method= RequestMethod.GET)
    public ModelAndView getAddFormView() {

        return new ModelAndView("addPerson", "person", new Person());

    }

    @RequestMapping(value="/addPerson", method= RequestMethod.POST)
    public String launchAddForm(@RequestParam("firstName")String firstName,
                                  @RequestParam("lastName")String lastName) {

        LOGGER.debug("Inserting person with FirstName : " + firstName + ", LastName : " + lastName);

        Person person = new Person();
        person.setPersonFirstName(firstName);
        person.setPersonLastName(lastName);
        personService.addPerson(person);

        return PERSON_LIST;

    }

    @RequestMapping(value="/getPersons", method= RequestMethod.GET)
    public ModelAndView getPersonListView() {

        List<Person> persons = personService.getPersons();

        LOGGER.debug("personList.size = " + persons.size());

        return new ModelAndView("personList", "persons", persons);

    }

    @RequestMapping(value="/getPersonsWithTasksBetweenDate", method= RequestMethod.GET)
    public ModelAndView getPersonsWithTasksBetweenDateListView(@RequestParam("startDate")String startDate,
                                                               @RequestParam("endDate")String endDate) {

        LOGGER.debug("StartDate : " + startDate +
                ", EndDate : " + endDate);

        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
        DateTime dtStart = formatter.parseDateTime(startDate);
        DateTime dtEnd = formatter.parseDateTime(endDate);
        List<Report> persons = personService.getPersonsWithTasksBetweenDate(dtStart, dtEnd);

        LOGGER.debug("personList.size = " + persons.size());

        return new ModelAndView("report", "persons", persons);

    }

    @RequestMapping(value = "/getPersonById", method = RequestMethod.GET)
    public ModelAndView getPersonByIdView(@RequestParam("id")Long personId) {

        LOGGER.debug("personId: " + personId);

        List<Person> persons;
        try {
            Person personFromDb = personService.getPersonById(personId);
            persons = new ArrayList<>(1);
            persons.add(personFromDb);
        } catch (IllegalArgumentException e) {
            persons = personService.getPersons();
        }

        return new ModelAndView("personList", "persons", persons);

    }

    @RequestMapping(value="/updatePerson", method= RequestMethod.GET)
    public ModelAndView getUpdateFormView(@RequestParam("id")Long personId,
                                          @RequestParam("firstName")String firstName,
                                          @RequestParam("lastName")String lastName) {

        LOGGER.debug("PersonId : " + personId +
                ", FirstName : " + firstName +
                ", LastName : " + lastName);

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName(firstName);
        person.setPersonLastName(lastName);

        return new ModelAndView("updatePerson", "person", person);

    }

    @RequestMapping(value="/updatePerson", method= {RequestMethod.PUT, RequestMethod.POST})
    public String launchUpdateForm(@RequestParam("id")Long personId,
                                   @RequestParam("firstName")String firstName,
                                   @RequestParam("lastName")String lastName) {

        LOGGER.debug("Updating person with PersonId : " + personId +
                ", FirstName : " + firstName +
                ", LastName : " + lastName);

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName(firstName);
        person.setPersonLastName(lastName);
        personService.updatePerson(person);

        return PERSON_LIST;

    }

    @RequestMapping(value = "/deletePerson", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String removePerson(@RequestParam("id")Long personId) {

        LOGGER.debug("Removing person with id = " + personId);

        personService.removePerson(personId);

        return PERSON_LIST;

    }

}