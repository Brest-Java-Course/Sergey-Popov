package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by beast on 22.11.14. At 13.44
 */
@RestController
@RequestMapping(value = "/rest/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> addTask(@RequestBody TaskHolder taskHolder) {

        LOGGER.debug("TaskRestController.addTask({})", taskHolder);
        Task taskFromDb = taskService.addTask(taskHolder.getTask(), taskHolder.getPersonId());
        return new ResponseEntity<>(taskFromDb, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasks() {

        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasksById(@PathVariable Long id) {

        List<Task> tasks = taskService.getTasksById(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        try {

            Task taskFromDb = taskService.getTaskById(id);
            return new ResponseEntity<>(taskFromDb, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity("Task not found for id = " + id + " error: "
            + e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateTask(@RequestBody Task task) {

        taskService.updateTask(task);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeTask(@PathVariable Long id ) {

        taskService.removeTask(id);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

}