package com.epam.brest.courses.service.impl;

import com.epam.brest.courses.dao.TaskDao;
import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.service.TaskService;
import com.epam.brest.courses.service.impl.datafixture.TaskDataFixture;
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

/**
 *
 */
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
public class TaskServiceImplMockTest {

    /**
     *
     */
    @Autowired
    private TaskService taskService;

    /**
     *
     */
    @Autowired
    private TaskDao taskDao;

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

        reset(taskDao);

    }
    /**
     * AddTask test
     * @throws Exception
     */
    @Test
    public void testAddTask() throws Exception {

        Task task = TaskDataFixture.getNewTask();
        Task task2 = TaskDataFixture.getExistTaskSame(1l);
        task2.setStartDate(task.getStartDate());

        expect(taskDao.addTask(task, 1l)).andReturn(task2);
        replay(taskDao);

        Task taskFromDb = taskService.addTask(task, 1l);

        assertEquals(task.getTaskName(), taskFromDb.getTaskName());
        assertEquals(task.getStartDate(), taskFromDb.getStartDate());
        assertEquals(task.getEndDate(), taskFromDb.getEndDate());
        assertEquals(task.getElapsedTime(), taskFromDb.getElapsedTime());
        assertEquals(task.getTaskState(), taskFromDb.getTaskState());

    }

    /**
     * getTasks test
     * @throws Exception
     */
    @Test
    public void testGetTasks() throws Exception {

        expect(taskDao.getTasks()).andReturn(TaskDataFixture.getSampleTaskList());
        replay(taskDao);

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

        expect(taskDao.getTasks()).andReturn(TaskDataFixture.getSampleTaskList());
        replay(taskDao);

        List<Task> tasks = taskService.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());

    }

    /**
     * getTaskById test
     * @throws Exception
     */
    @Test
    public void testGetTaskById() throws Exception {

        Task task = TaskDataFixture.getExistTask(1l);
        expect(taskDao.getTaskById(task.getTaskId())).andReturn(task);
        replay(taskDao);

        Task taskFromDb = taskService.getTaskById(task.getTaskId());

        assertEquals(task.getTaskName(), taskFromDb.getTaskName());
        assertEquals(task.getStartDate(), taskFromDb.getStartDate());
        assertEquals(task.getEndDate(), taskFromDb.getEndDate());
        assertEquals(task.getElapsedTime(), taskFromDb.getElapsedTime());
        assertEquals(task.getTaskState(), taskFromDb.getTaskState());

    }

    /**
     * updateTask test
     * @throws Exception
     */
    @Test
    public void testUpdateTask() throws Exception {

        taskDao.updateTask(anyObject(Task.class));
        replay(taskDao);
        Task task = TaskDataFixture.getExistTask(1l);
        task.setTaskName("TestMockUpdate");
        taskService.updateTask(task);

    }

    /**
     * removeTask test
     * @throws Exception
     */
    @Test
    public void testRemoveTask() throws Exception {

        taskDao.removeTask(1l);
        replay(taskDao);
        taskService.removeTask(1l);

    }

}