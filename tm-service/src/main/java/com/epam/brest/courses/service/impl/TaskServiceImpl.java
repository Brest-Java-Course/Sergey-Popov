package com.epam.brest.courses.service.impl;

import com.epam.brest.courses.dao.TaskDao;
import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by beast on 21.11.14. At 11.42
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    @Autowired
    private TaskDao taskDao;

    @Override
    public final Task addTask(final Task task, final Long personId) {

        LOGGER.debug("TaskServiceImpl.addTask({})", task);

        Assert.notNull(task);
        Assert.notNull(personId, "Person Id should be specified!");
        Assert.isNull(task.getTaskId(), "Task Id should not be specified!");
        Assert.notNull(task.getTaskName(), "Task Name should be specified!");
        Assert.notNull(task.getStartDate(),
                "Task start date should be specified!");
        Assert.isNull(task.getEndDate(),
                "Task end date should not be specified!");
        Assert.isNull(task.getElapsedTime(),
                "Task elapsed time should not be specified!");
        Assert.notNull(task.getTaskState(), "Task state should be specified");

        Task taskFromDb = taskDao.addTask(task, personId);

        LOGGER.debug("TaskServiceImpl.addTask() : id = {}",
                taskFromDb.getTaskId());
        return taskFromDb;

    }

    @Override
    public final List<Task> getTasks() {

        LOGGER.debug("TaskServiceImpl.getTasks()");

        List<Task> tasks;
        tasks = taskDao.getTasks();

        LOGGER.debug("TaskServiceImpl.getTasks() : list.size = {}",
                tasks.size());
        return tasks;

    }

    @Override
    public final List<Task> getTasksById(final Long personId) {

        LOGGER.debug("TaskServiceImpl.getTasksById(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");

        List<Task> tasks;
        tasks = taskDao.getTasksById(personId);

        LOGGER.debug("TaskServiceImpl.getTasksById() : list.size = {}",
                tasks.size());
        return tasks;

    }

    @Override
    public final Task getTaskById(final Long taskId) {

        LOGGER.debug("TaskServiceImpl.getTaskById(taskId = {})", taskId);

        Assert.notNull(taskId, "Task Id should be specified!");

        Task taskFromDb = taskDao.getTaskById(taskId);

        LOGGER.debug("TaskServiceImpl.getTaskById() : task = {}", taskFromDb);
        return taskFromDb;

    }

    @Override
    public final void updateTask(final Task task) {

        LOGGER.debug("TaskServiceImpl.updateTask({})", task);

        Assert.notNull(task);

        taskDao.updateTask(task);

        LOGGER.debug("TaskServiceImpl.updateTask() : taskUpdated = {}", task);

    }

    @Override
    public final void removeTask(final Long taskId) {

        LOGGER.debug("TaskServiceImpl.removeTask(taskId = {})", taskId);

        Assert.notNull(taskId, "Task Id should be specified!");

        taskDao.removeTask(taskId);

        LOGGER.debug("TaskServiceImpl.removeTask() : id taskRemoved = {}",
                taskId);

    }

}
