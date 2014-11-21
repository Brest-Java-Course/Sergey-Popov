package com.epam.brest.courses.dao.impl;

import com.epam.brest.courses.dao.TaskDao;
import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by beast on 19.11.14. At 11.59
 */
@Repository
public class TaskDaoImpl implements TaskDao {

    @PersistenceContext
    protected EntityManager emf;

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional
    public Task addTask(Task task, Long personId) {

        LOGGER.debug("addTask({})", task);

        Assert.notNull(task);
        Assert.notNull(personId, "Person Id should be specified!");
        Assert.isNull(task.getTaskId(), "Task Id should not be specified!");
        Assert.notNull(task.getTaskName(), "Task Name should be specified!");
        Assert.notNull(task.getStartDate(), "Task start date should be specified!");
        Assert.isNull(task.getEndDate(), "Task end date should not be specified!");
        Assert.isNull(task.getElapsedTime(), "Task elapsed time should not be specified!");
        Assert.notNull(task.isTaskState(), "Task state should be specified");

        Person personFromDb = emf.find(Person.class, personId);
        task.setPerson(personFromDb);
        Task taskFromDb = emf.merge(task);

        LOGGER.debug("addTask() : id = {}", taskFromDb.getTaskId());
        return taskFromDb;

    }

    @Override
    @Transactional
    public List<Task> getTasks() {

        LOGGER.debug("getTasks()");

        List<Task> tasks;
        TypedQuery<Task> query = emf.createNamedQuery("Task.findAll", Task.class);
        tasks = query.getResultList();

        LOGGER.debug("getTasks() : list.size = {}", tasks.size());
        return tasks;

    }

    @Override
    @Transactional
    public List<Task> getTasksById(Long personId) {

        LOGGER.debug("getTasksById(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");

        List<Task> tasks;
        Query query = emf.createQuery("SELECT t FROM Task t WHERE t.person.personId = :personId").setParameter("personId", personId);
        tasks = query.getResultList();

        LOGGER.debug("getTasksById() : list.size = {}", tasks.size());
        return tasks;

    }

    @Override
    @Transactional
    public Task getTaskById(Long taskId) {

        LOGGER.debug("getTaskById(taskId = {})", taskId);

        Assert.notNull(taskId, "Task Id should be specified!");

        Task taskFromDb = emf.find(Task.class, taskId);

        LOGGER.debug("getTaskById() : task = {}", taskFromDb);
        return taskFromDb;

    }

    @Override
    @Transactional
    public void updateTask(Task task) {

        LOGGER.debug("updateTask({})", task);

        Assert.notNull(task);

        Task taskUpdate = emf.find(Task.class, task.getTaskId());
        taskUpdate.setTaskName(task.getTaskName());
        taskUpdate.setStartDate(task.getStartDate());
        taskUpdate.setEndDate(task.getEndDate());
        taskUpdate.setElapsedTime(task.getElapsedTime());
        taskUpdate.setTaskState(task.isTaskState());

        LOGGER.debug("updateTask() : taskUpdated = {}", taskUpdate);

    }

    @Override
    @Transactional
    public void removeTask(Long taskId) {

        LOGGER.debug("removeTask(taskId = {})", taskId);

        Assert.notNull(taskId, "Task Id should be specified!");

        Task taskFromDb = emf.find(Task.class, taskId);
        emf.remove(emf.contains(taskFromDb) ? taskFromDb : emf.merge(taskFromDb));

        LOGGER.debug("removeTask() : taskRemoved = {}", taskFromDb);

    }

}
