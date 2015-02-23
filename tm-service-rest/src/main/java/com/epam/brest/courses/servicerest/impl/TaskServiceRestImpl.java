package com.epam.brest.courses.servicerest.impl;

import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.rest.TaskHolder;
import com.epam.brest.courses.servicerest.TaskServiceRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by beast on 21.11.14. At 11.42
 */
@Service
public class TaskServiceRestImpl implements TaskServiceRest {

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    private static final String REST_URL = "http://localhost:8080/rest/tasks";

    /**
     *
     */
    private static final String REST_GET_TASKS_BY_ID_URL = "/person/{id}";

    /**
     *
     */
    private static final String REST_GET_TASK_BY_ID_URL = "/{id}";

    /**
     *
     */
    private static final String REST_REMOVE_TASK_BY_ID_URL = "/{id}";

    @Override
    public final Task addTask(final Task task, final Long personId) {

        LOGGER.debug("TaskServiceRestImpl.addTask({})", task);
        LOGGER.debug("TaskServiceRestImpl.addTask({})", personId);

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


        RestTemplate restTemplate = new RestTemplate();
        TaskHolder taskHolder = new TaskHolder();
        taskHolder.setTask(task);
        taskHolder.setPersonId(personId);
        Task taskFromDb = restTemplate.postForObject(
                REST_URL,
                taskHolder,
                Task.class);

        LOGGER.debug("TaskServiceRestImpl.addTask() : id = {}",
                taskFromDb.getTaskId());
        return taskFromDb;

    }

    @Override
    public final List<Task> getTasks() {

        LOGGER.debug("TaskServiceRestImpl.getTasks()");

        RestTemplate restTemplate = new RestTemplate();
        Task[] taskList = restTemplate.getForObject(REST_URL, Task[].class);
        List<Task> tasks = Arrays.asList(taskList);
        LOGGER.debug("TaskServiceRestImpl.getTasks() : list.size = {}",
                tasks.size());
        return tasks;

    }

    @Override
    public final List<Task> getTasksById(final Long personId) {

        LOGGER.debug("TaskServiceRestImpl.getTasksById(personId = {})",
                personId);

        Assert.notNull(personId, "Person Id should be specified!");

        RestTemplate restTemplate = new RestTemplate();
        Task[] taskList = restTemplate.getForObject(
                REST_URL + REST_GET_TASKS_BY_ID_URL,
                Task[].class,
                personId);
        List<Task> tasks = Arrays.asList(taskList);

        LOGGER.debug("TaskServiceImpl.getTasksById() : list.size = {}",
                tasks.size());
        return tasks;

    }

    @Override
    public final Task getTaskById(final Long taskId) {

        LOGGER.debug("TaskServiceRestImpl.getTaskById(taskId = {})", taskId);

        Assert.notNull(taskId, "Task Id should be specified!");
        RestTemplate restTemplate = new RestTemplate();
        Task taskFromDb = restTemplate.getForObject(
                REST_URL + REST_GET_TASK_BY_ID_URL,
                Task.class,
                taskId);

        LOGGER.debug("TaskServiceRestImpl.getTaskById() : task = {}",
                taskFromDb);
        return taskFromDb;

    }

    @Override
    public final void updateTask(final Task task) {

        LOGGER.debug("TaskServiceRestImpl.updateTask({})", task);

        Assert.notNull(task);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_URL, task);

        LOGGER.debug("TaskServiceRestImpl.updateTask() : taskUpdated = {}",
                task);

    }

    @Override
    public final void removeTask(final Long taskId) {

        LOGGER.debug("TaskServiceRestImpl.removeTask(taskId = {})", taskId);

        Assert.notNull(taskId, "Task Id should be specified!");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_URL + REST_REMOVE_TASK_BY_ID_URL, taskId);

        LOGGER.debug("TaskServiceRestImpl.removeTask() : id taskRemoved = {}",
                taskId);

    }

}
