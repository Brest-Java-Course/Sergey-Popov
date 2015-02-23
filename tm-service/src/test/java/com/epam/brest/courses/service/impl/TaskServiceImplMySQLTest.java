package com.epam.brest.courses.service.impl;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.service.PersonService;
import com.epam.brest.courses.service.TaskService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
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

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-hsql-test.xml"})
@TestExecutionListeners(
        listeners = {
                DependencyInjectionTestExecutionListener.class,
                DirtiesContextTestExecutionListener.class,
                TransactionalTestExecutionListener.class,
                SqlScriptsTestExecutionListener.class
        }
)
public class TaskServiceImplMySQLTest {

    /**
     *
     */
    @Autowired
    private PersonService personService;

    /**
     *
     */
    @Autowired
    private TaskService taskService;

    /**
     * AddTask test
     * @throws Exception
     */
    @Test
    public void testAddTask() throws Exception {


        Person person = new Person();
        person.setPersonFirstName("testATFirstName");
        person.setPersonLastName("testATLastName");

        Long personId = personService.addPerson(person).getPersonId();

        List<Task> tasks = taskService.getTasks();
        int sizeBefore = tasks.size();

        Task task = new Task();
        task.setTaskName("testTaskNameAT");
        task.setStartDate(new DateTime());
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        taskService.addTask(task, personId);

        tasks = taskService.getTasks();
        int sizeAfter = tasks.size() - 1;

        assertEquals(sizeBefore, sizeAfter);

    }

    /**
     * getTasks test
     * @throws Exception
     */
    @Test
    public void testGetTasks() throws Exception {

        List<Task> tasks = taskService.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());

    }

    /**
     * getTasksById test
     * @throws Exception
     */
    @Test
    public void testGetTasksById() throws Exception {

        List<Task> tasks = taskService.getTasksById(1l);

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

        Long personId = personService.addPerson(person).getPersonId();

        Task testTaskAdd = new Task();
        testTaskAdd.setTaskName("testTaskNameGTBI");
        testTaskAdd.setStartDate(new DateTime());
        testTaskAdd.setEndDate(null);
        testTaskAdd.setElapsedTime(null);
        testTaskAdd.setTaskState(true);

        Task testTaskGet = taskService.addTask(testTaskAdd, personId);

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

        Long personId = personService.addPerson(person).getPersonId();

        Task testTaskAdd = new Task();
        testTaskAdd.setTaskName("testTaskNameUT");
        testTaskAdd.setStartDate(new DateTime());
        testTaskAdd.setEndDate(null);
        testTaskAdd.setElapsedTime(null);
        testTaskAdd.setTaskState(true);

        Task taskFromDb = taskService.addTask(testTaskAdd, personId);
        Long taskId = taskFromDb.getTaskId();

        Task testTaskUpdate = new Task();
        testTaskUpdate.setTaskId(taskId);
        testTaskUpdate.setTaskName("testTaskNameUTUpdated");
        testTaskUpdate.setStartDate(taskFromDb.getStartDate());
        DateTime currentDateTime = new DateTime();
        testTaskUpdate.setEndDate(currentDateTime);
        testTaskUpdate.setElapsedTime(new Period(taskFromDb.getStartDate(), currentDateTime, PeriodType.minutes()).getMinutes());
        testTaskUpdate.setTaskState(false);

        taskService.updateTask(testTaskUpdate);

        Task testTaskGet = taskService.getTaskById(taskId);

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

        List<Task> tasks = taskService.getTasks();
        int sizeBefore = tasks.size();

        taskService.removeTask(tasks.get(tasks.size() - 1).getTaskId());

        tasks = taskService.getTasks();
        int sizeAfter = tasks.size();

        assertEquals(sizeBefore - 1, sizeAfter);

    }

}