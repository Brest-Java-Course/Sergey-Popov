package com.epam.brest.courses.service.impl.DataFixture;

import com.epam.brest.courses.domain.Task;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 21.11.14. At 16.13
 */
public class TaskDataFixture {

    public static Task getNewTask() {

        Task task = new Task();
        task.setTaskName("MockTaskName");
        task.setStartDate(new DateTime());
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;

    }

    public static Task getNewTask(Long taskId) {

        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName("MockTaskName" + taskId);
        task.setStartDate(new DateTime());
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;
    }

    public static Task getExistTaskSame(Long taskId) {

        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName("MockTaskName");
        task.setStartDate(new DateTime());
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;

    }

    public static Task getExistTask(Long taskId) {

        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName("MockTaskName" + taskId);
        task.setStartDate(new DateTime());
        task.setEndDate(null);
        task.setElapsedTime(null);
        task.setTaskState(true);

        return task;

    }

    public static List<Task> getSampleTaskList() {
        List<Task> list = new ArrayList<>(3);
        list.add(TaskDataFixture.getNewTask(1L));
        list.add(TaskDataFixture.getNewTask(2L));
        list.add(TaskDataFixture.getNewTask(3L));

        return list;
    }

}
