package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by beast on 22.11.14. At 13.44
 */
@RestController
@RequestMapping(value = "/rest/tasks")
public class TaskRestController {

    /**
     *
     */
    @Autowired
    private TaskService taskService;

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     * @param taskHolder taskHolder
     * @return ResponseEntity<>
     */
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<Task> addTask(
            @RequestBody final TaskHolder taskHolder) {

        LOGGER.debug("TaskRestController.addTask({})", taskHolder);
        Task taskFromDb = taskService.addTask(taskHolder.getTask(),
                taskHolder.getPersonId());
        return new ResponseEntity<>(taskFromDb, HttpStatus.CREATED);

    }

    /**
     *
     * @return ResponseEntity<>
     */
    @RequestMapping(method = RequestMethod.GET)
    public final ResponseEntity<List<Task>> getTasks() {

        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    /**
     *
     * @param id id
     * @return ResponseEntity<>
     */
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public final ResponseEntity<List<Task>> getTasksById(
            @PathVariable final Long id) {

        List<Task> tasks = taskService.getTasksById(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    /**
     *
     * @param id id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final ResponseEntity<Task> getTaskById(@PathVariable final Long id) {

        try {

            Task taskFromDb = taskService.getTaskById(id);
            return new ResponseEntity<>(taskFromDb, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity("Task not found for id = "
                    + id + " error: "
                    + e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    /**
     *
     * @param task task
     * @return ResponseEntity<>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public final ResponseEntity<String> updateTask(
            @RequestBody final Task task) {

        taskService.updateTask(task);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

    /**
     *
     * @param id id
     * @return ResponseEntity<>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final ResponseEntity<String> removeTask(
            @PathVariable final Long id) {

        taskService.removeTask(id);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

}
