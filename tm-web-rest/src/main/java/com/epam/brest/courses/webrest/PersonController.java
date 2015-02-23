package com.epam.brest.courses.webrest;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import com.epam.brest.courses.servicerest.PersonServiceRest;
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
@RequestMapping("/client/person")
public class PersonController {

    /**
     *
     */
    private static final String PERSON_LIST =
            "redirect:/client/person/getPersons";

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    @Autowired
    private PersonServiceRest personService;

    /**
     *
     * @return PERSON_LIST
     */
    @RequestMapping
    public final String init() {

        return PERSON_LIST;

    }

    /**
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/addPerson", method = RequestMethod.GET)
    public final ModelAndView getAddFormView() {

        return new ModelAndView("addPerson", "person", new Person());

    }

    /**
     *
     * @param firstName firstName
     * @param lastName lastName
     * @return PERSON_LIST
     */
    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public final String launchAddForm(
            @RequestParam("firstName") final String firstName,
            @RequestParam("lastName") final String lastName) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Inserting person with FirstName : "
                    + firstName + ", LastName : " + lastName);
        }

        Person person = new Person();
        person.setPersonFirstName(firstName);
        person.setPersonLastName(lastName);
        personService.addPerson(person);

        return PERSON_LIST;

    }

    /**
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/getPersons", method = RequestMethod.GET)
    public final ModelAndView getPersonListView() {

        List<Person> persons = personService.getPersons();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("personList.size = " + persons.size());
        }

        return new ModelAndView("personList", "persons", persons);

    }

    /**
     *
     * @param startDate startDate
     * @param endDate endDate
     * @return ModelAndView
     */
    @RequestMapping(value = "/getPersonsWithTasksBetweenDate",
            method = RequestMethod.GET)
    public final ModelAndView getPersonsWithTasksBetweenDateListView(
            @RequestParam("startDate") final String startDate,
            @RequestParam("endDate") final String endDate) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("StartDate : " + startDate
                    + ", EndDate : " + endDate);
        }

        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
        DateTime dtStart = formatter.parseDateTime(startDate);
        DateTime dtEnd = formatter.parseDateTime(endDate);
        List<Report> persons = personService.getPersonsWithTasksBetweenDate(
                dtStart, dtEnd);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("personList.size = " + persons.size());
        }

        return new ModelAndView("report", "persons", persons);

    }

    /**
     *
     * @param personId personId
     * @return ModelAndView
     */
    @RequestMapping(value = "/getPersonById", method = RequestMethod.GET)
    public final ModelAndView getPersonByIdView(
            @RequestParam("id") final Long personId) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("personId: " + personId);
        }

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

    /**
     *
     * @param personId personId
     * @param firstName firstName
     * @param lastName lastName
     * @return ModelAndView
     */
    @RequestMapping(value = "/updatePerson", method = RequestMethod.GET)
    public final ModelAndView getUpdateFormView(
            @RequestParam("id") final Long personId,
            @RequestParam("firstName") final String firstName,
            @RequestParam("lastName") final String lastName) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("PersonId : " + personId
                    + ", FirstName : " + firstName
                    + ", LastName : " + lastName);
        }

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName(firstName);
        person.setPersonLastName(lastName);

        return new ModelAndView("updatePerson", "person", person);

    }

    /**
     *
     * @param personId personId
     * @param firstName firstName
     * @param lastName lastName
     * @return PERSON_LIST
     */
    @RequestMapping(value = "/updatePerson", method = {
            RequestMethod.PUT,
            RequestMethod.POST
    })
    public final String launchUpdateForm(
            @RequestParam("id") final Long personId,
            @RequestParam("firstName") final String firstName,
            @RequestParam("lastName") final String lastName) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Updating person with PersonId : " + personId
                    + ", FirstName : " + firstName
                    + ", LastName : " + lastName);
        }

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonFirstName(firstName);
        person.setPersonLastName(lastName);
        personService.updatePerson(person);

        return PERSON_LIST;

    }

    /**
     *
     * @param personId personId
     * @return PERSON_LIST
     */
    @RequestMapping(value = "/deletePerson", method = {
            RequestMethod.DELETE,
            RequestMethod.POST
    })
    public final String removePerson(@RequestParam("id") final Long personId) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Removing person with id = " + personId);
        }

        personService.removePerson(personId);

        return PERSON_LIST;

    }

}
