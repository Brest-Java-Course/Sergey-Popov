package com.epam.brest.courses.servicerest.impl;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.servicerest.PersonServiceRest;
import com.epam.brest.courses.servicerest.TaskServiceRest;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
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
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-rest-test.xml"})
@TestExecutionListeners(
        listeners = {
                DependencyInjectionTestExecutionListener.class,
                DirtiesContextTestExecutionListener.class
        }
)
public class TaskServiceRestImplTest {

    @Autowired
    private PersonServiceRest personServiceRest;

    @Autowired
    private TaskServiceRest taskServiceRest;

    /**
     * AddTask test
     * @throws Exception
     */
    @Test
    public void testAddTask() throws Exception {


        Person person = new Person();
        person.setPersonFirstName("testATFirstName");
        person.setPersonLastName("testATLastName");

        Long personId = personServiceRest.addPerson(person).getPersonId();

        List<Task> tasks = taskServiceRest.getTasks();
        int sizeBefore = tasks.size();

        Task task = new Task();
        task.setTaskName("testTaskNameAT");
        task.setStartDate(new DateTime());
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        taskServiceRest.addTask(task, personId);

        tasks = taskServiceRest.getTasks();
        int sizeAfter = tasks.size() - 1;

        assertEquals(sizeBefore, sizeAfter);

    }

    /**
     * getTasks test
     * @throws Exception
     */
    @Test
    public void testGetTasks() throws Exception {

        List<Task> tasks = taskServiceRest.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());

    }

    /**
     * getTasksById test
     * @throws Exception
     */
    @Test
    public void testGetTasksById() throws Exception {

        List<Task> tasks = taskServiceRest.getTasksById(1l);

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());

    }

    /**
     * getTaskById test
     * @throws Exception
     */
    @Test
    public void testGetTaskById() throws Exception {

        Person person = new Person();
        person.setPersonFirstName("testGTBIFirstName");
        person.setPersonLastName("testGTBILastName");

        Long personId = personServiceRest.addPerson(person).getPersonId();

        Task testTaskAdd = new Task();
        testTaskAdd.setTaskName("testTaskNameGTBI");
        testTaskAdd.setStartDate(new DateTime());
        testTaskAdd.setEndDate(null);
        testTaskAdd.setElapsedTime(null);
        testTaskAdd.setTaskState(true);

        Task testTaskGet = taskServiceRest.addTask(testTaskAdd, personId);

        assertNotNull(testTaskGet);
        assertNotNull(testTaskGet.getTaskId());
        assertNotNull(testTaskGet.getTaskName());
        assertNotNull(testTaskGet.getStartDate());
        assertNull(testTaskGet.getEndDate());
        assertNull(testTaskGet.getElapsedTime());
        assertTrue(testTaskGet.getTaskState());
        assertEquals(testTaskAdd.getTaskName(), testTaskGet.getTaskName());
        assertEquals(testTaskAdd.getStartDate(), testTaskGet.getStartDate());
        assertEquals(testTaskAdd.getEndDate(), testTaskGet.getEndDate());
        assertEquals(testTaskAdd.getElapsedTime(), testTaskGet.getElapsedTime());
        assertEquals(testTaskAdd.getTaskState(), testTaskGet.getTaskState());

    }

    /**
     * updateTask test
     * @throws Exception
     */
    @Test
    public void testUpdateTask() throws Exception {

        Person person = new Person();
        person.setPersonFirstName("testUTFirstName");
        person.setPersonLastName("testUTLastName");

        Long personId = personServiceRest.addPerson(person).getPersonId();

        Task testTaskAdd = new Task();
        testTaskAdd.setTaskName("testTaskNameUT");
        testTaskAdd.setStartDate(new DateTime());
        testTaskAdd.setEndDate(null);
        testTaskAdd.setElapsedTime(null);
        testTaskAdd.setTaskState(true);

        Task taskFromDb = taskServiceRest.addTask(testTaskAdd, personId);
        Long id = taskFromDb.getTaskId();

        Task testTaskUpdate = new Task();
        testTaskUpdate.setTaskId(id);
        testTaskUpdate.setTaskName("testTaskNameUTUpdated");
        testTaskUpdate.setStartDate(taskFromDb.getStartDate());
        DateTime currentDateTime = new DateTime();
        testTaskUpdate.setEndDate(currentDateTime);
        testTaskUpdate.setElapsedTime(new Period(taskFromDb.getStartDate(), currentDateTime, PeriodType.minutes()).getMinutes());
        testTaskUpdate.setTaskState(false);

        taskServiceRest.updateTask(testTaskUpdate);

        Task testTaskGet = taskServiceRest.getTaskById(id);

        assertNotNull(testTaskGet);
        assertNotNull(testTaskGet.getTaskId());
        assertNotNull(testTaskGet.getTaskName());
        assertNotNull(testTaskGet.getStartDate());
        assertNotNull(testTaskGet.getEndDate());
        assertNotNull(testTaskGet.getElapsedTime());
        assertFalse(testTaskGet.getTaskState());
        assertEquals(testTaskUpdate.getTaskId(), testTaskGet.getTaskId());
        assertEquals(testTaskUpdate.getTaskName(), testTaskGet.getTaskName());
        assertEquals(testTaskUpdate.getStartDate(), testTaskGet.getStartDate());
        assertEquals(testTaskUpdate.getEndDate(), testTaskGet.getEndDate());
        assertEquals(testTaskUpdate.getElapsedTime(), testTaskGet.getElapsedTime());
        assertEquals(testTaskUpdate.getTaskState(), testTaskGet.getTaskState());

    }

    /**
     * removeTask test
     * @throws Exception
     */
    @Test
    public void testRemoveTask() throws Exception {

        List<Task> tasks = taskServiceRest.getTasks();
        int sizeBefore = tasks.size();

        taskServiceRest.removeTask(tasks.get(tasks.size() - 1).getTaskId());

        tasks = taskServiceRest.getTasks();
        int sizeAfter = tasks.size();

        assertEquals(sizeBefore - 1, sizeAfter);

    }

}