package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import org.joda.time.DateTime;

import java.util.List;

/**
 * A simple DAO interface to handle the database operation required to manipulate a Person entity.
 * Created by beast on 19.11.14. At 11.17
 * @author Sergey Popov
 * @version 1.0, November 2014
 */
public interface PersonDao {

    /**
     * Inserts the specified person to the database.
     *
     * @param person person to be inserted to the database
     * @return the person
     */
    public Person addPerson(Person person);

    /**
     * Returns a list containing all of the persons in the database.
     *
     * @return a list containing all of the persons in the database
     * @see java.util.List
     */
    public List<Person> getPersons();

    /**
     * Returns a list containing all of the persons in the database.
     *
     * @param startDate start date
     * @param endDate end date
     * @return a list containing all of the persons in the database with the specified task`s date
     * @see java.util.List
     */
    public List<Report> getPersonsWithTasksBetweenDate(DateTime startDate, DateTime endDate);

    /**
     * Returns the person with the specified personId from database.
     *
     * @param personId id of the person to return
     * @return the person with the specified personId from the database
     */
    public Person getPersonById(Long personId);

    /**
     * Replaces the person in the database with the specified person.
     *
     * @param person person to be stored in the database
     */
    public void updatePerson(Person person);

    /**
     * Removes the person with the specified personId from the database.
     *
     * @param personId id of the person to be removed
     */
    public void removePerson(Long personId);

}