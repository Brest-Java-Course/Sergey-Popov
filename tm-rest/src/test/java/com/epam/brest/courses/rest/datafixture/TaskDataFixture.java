package com.epam.brest.courses.rest.datafixture;

import com.epam.brest.courses.domain.Task;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 21.11.14. At 16.13
 */
public class TaskDataFixture {

    /**
     *
     */
    private static final DateTime DATE_TIME = new DateTime(2014, 11, 22, 17, 26, 0);

    /**
     *
     */
    private static final String MOCK_TASK_NAME = "MockTaskName";

    /**
     *
     * @return task
     */
    public static Task getNewTask() {

        Task task = new Task();
        task.setTaskName(MOCK_TASK_NAME);
        task.setStartDate(DATE_TIME);
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;

    }

    /**
     *
     * @param taskId taskId
     * @return task
     */
    public static Task getNewTask(final Long taskId) {

        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName(MOCK_TASK_NAME + taskId);
        task.setStartDate(DATE_TIME);
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;
    }

    /**
     *
     * @param taskId taskId
     * @return task
     */
    public static Task getExistTaskSame(final Long taskId) {

        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName(MOCK_TASK_NAME);
        task.setStartDate(DATE_TIME);
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;

    }

    /**
     *
     * @param taskId taskId
     * @return task
     */
    public static Task getExistTask(final Long taskId) {

        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName(MOCK_TASK_NAME + taskId);
        task.setStartDate(DATE_TIME);
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;

    }

    /**
     *
     * @return list
     */
    public static List<Task> getSampleTaskList() {
        List<Task> list = new ArrayList<>(3);
        list.add(TaskDataFixture.getNewTask(1L));
        list.add(TaskDataFixture.getNewTask(2L));
        list.add(TaskDataFixture.getNewTask(3L));

        return list;
    }

}
