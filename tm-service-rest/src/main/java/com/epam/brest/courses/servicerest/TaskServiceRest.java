package com.epam.brest.courses.servicerest;

import com.epam.brest.courses.domain.Task;

import java.util.List;

/**
 * A simple TaskService interface to handle the operation required
 * to manipulate a Task entity.
 * Created by beast on 21.11.14. At 11.22
 * @author Sergey Popov
 * @version 1.0, November 2014
 */
public interface TaskServiceRest {

    /**
     * Inserts the specified task to the database.
     *
     * @param task task to be inserted to the database
     * @param personId id of the person
     * @return the task
     */
    Task addTask(Task task, Long personId);

    /**
     * Returns a list containing all of the tasks in the database.
     *
     * @return a list containing all of the tasks in the database
     * @see java.util.List
     */
    List<Task> getTasks();

    /**
     * Returns a list containing the tasks with the specified personId
     * from the database.
     *
     * @param personId id of the person
     * @return a list containing the tasks with the specified personId
     * from the database
     * @see java.util.List
     */
    List<Task> getTasksById(Long personId);

    /**
     * Returns the task with the specified taskId from the database.
     *
     * @param taskId id of the task to return
     * @return the task with the specified taskId from the database
     */
    Task getTaskById(Long taskId);

    /**
     * Replaces the task in the database with the specified task.
     *
     * @param task task to be stored in the database
     */
    void updateTask(Task task);

    /**
     * Removes the task with the specified taskId from the database.
     *
     * @param taskId id of the task to be removed
     */
    void removeTask(Long taskId);

}
