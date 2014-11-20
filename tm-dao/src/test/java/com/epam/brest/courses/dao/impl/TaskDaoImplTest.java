package com.epam.brest.courses.dao.impl;

import com.epam.brest.courses.dao.PersonDao;
import com.epam.brest.courses.dao.TaskDao;
import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Task;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class TaskDaoImplTest {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private PersonDao personDao;

    /**
     * AddTask test
     * @throws Exception
     */
    @Test
    public void testAddTask() throws Exception {


        Person person = new Person();
        person.setPersonFirstName("testATFirstName");
        person.setPersonLastName("testATLastName");

        Long personId = personDao.addPerson(person).getPersonId();

        List<Task> tasks = taskDao.getTasks();
        int sizeBefore = tasks.size();

        Task task = new Task();
        task.setTaskName("testTaskNameAT");
        task.setStartDate(new DateTime());
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        taskDao.addTask(task, personId);

        tasks = taskDao.getTasks();
        int sizeAfter = tasks.size() - 1;

        assertEquals(sizeBefore, sizeAfter);

    }

    /**
     * getTasks test
     * @throws Exception
     */
    @Test
    public void testGetTasks() throws Exception {

        List<Task> tasks = taskDao.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());

    }

    /**
     * getTasksById test
     * @throws Exception
     */
    @Test
    public void testGetTasksById() throws Exception {

        List<Task> tasks = taskDao.getTasksById(1l);

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

        Long personId = personDao.addPerson(person).getPersonId();

        Task testTaskAdd = new Task();
        testTaskAdd.setTaskName("testTaskNameGTBI");
        testTaskAdd.setStartDate(new DateTime());
        testTaskAdd.setEndDate(null);
        testTaskAdd.setElapsedTime(null);
        testTaskAdd.setTaskState(true);

        Task testTaskGet = taskDao.addTask(testTaskAdd, personId);

        assertNotNull(testTaskGet);
        assertNotNull(testTaskGet.getTaskId());
        assertNotNull(testTaskGet.getTaskName());
        assertNotNull(testTaskGet.getStartDate());
        assertNull(testTaskGet.getEndDate());
        assertNull(testTaskGet.getElapsedTime());
        assertTrue(testTaskGet.isTaskState());
        assertEquals(testTaskAdd.getTaskName(), testTaskGet.getTaskName());
        assertEquals(testTaskAdd.getStartDate(), testTaskGet.getStartDate());
        assertEquals(testTaskAdd.getEndDate(), testTaskGet.getEndDate());
        assertEquals(testTaskAdd.getElapsedTime(), testTaskGet.getElapsedTime());
        assertEquals(testTaskAdd.isTaskState(), testTaskGet.isTaskState());

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

        Long personId = personDao.addPerson(person).getPersonId();

        Task testTaskAdd = new Task();
        testTaskAdd.setTaskName("testTaskNameUT");
        testTaskAdd.setStartDate(new DateTime());
        testTaskAdd.setEndDate(null);
        testTaskAdd.setElapsedTime(null);
        testTaskAdd.setTaskState(true);

        Task taskFromDb = taskDao.addTask(testTaskAdd, personId);
        Long id = taskFromDb.getTaskId();

        Task testTaskUpdate = new Task();
        testTaskUpdate.setTaskId(id);
        testTaskUpdate.setTaskName("testTaskNameUTUpdated");
        testTaskUpdate.setStartDate(taskFromDb.getStartDate());
        DateTime currentDateTime = new DateTime();
        testTaskUpdate.setEndDate(currentDateTime);
        testTaskUpdate.setElapsedTime(new Period(taskFromDb.getStartDate(), currentDateTime, PeriodType.minutes()).getMinutes());
        testTaskUpdate.setTaskState(false);

        taskDao.updateTask(testTaskUpdate);

        Task testTaskGet = taskDao.getTaskById(id);

        assertNotNull(testTaskGet);
        assertNotNull(testTaskGet.getTaskId());
        assertNotNull(testTaskGet.getTaskName());
        assertNotNull(testTaskGet.getStartDate());
        assertNotNull(testTaskGet.getEndDate());
        assertNotNull(testTaskGet.getElapsedTime());
        assertFalse(testTaskGet.isTaskState());
        assertEquals(testTaskUpdate.getTaskId(), testTaskGet.getTaskId());
        assertEquals(testTaskUpdate.getTaskName(), testTaskGet.getTaskName());
        assertEquals(testTaskUpdate.getStartDate(), testTaskGet.getStartDate());
        assertEquals(testTaskUpdate.getEndDate(), testTaskGet.getEndDate());
        assertEquals(testTaskUpdate.getElapsedTime(), testTaskGet.getElapsedTime());
        assertEquals(testTaskUpdate.isTaskState(), testTaskGet.isTaskState());

    }

    /**
     * removeTask test
     * @throws Exception
     */
    @Test
    public void testRemoveTask() throws Exception {

        List<Task> tasks = taskDao.getTasks();
        int sizeBefore = tasks.size();

        taskDao.removeTask(tasks.get(tasks.size() - 1).getTaskId());

        tasks = taskDao.getTasks();
        int sizeAfter = tasks.size();

        assertEquals(sizeBefore - 1, sizeAfter);

    }

}