package com.epam.brest.courses.web;

import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 25.11.14. At 18.56
 */
@Controller
@RequestMapping("/mvc/task")
public class TaskController {

    /**
     *
     */
    private static final String TASK_LIST = "redirect:/mvc/task/getTasks";

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    @Autowired
    private TaskService taskService;

    /**
     *
     * @return TASK_LIST
     */
    @RequestMapping
    public final String init() {

        return TASK_LIST;

    }

    /**
     *
     * @param personId personId
     * @return modelAndView
     */
    @RequestMapping(value = "/addTask", method = RequestMethod.GET)
    public final ModelAndView getAddFormView(
            @RequestParam("personId") final Long personId) {

        ModelAndView modelAndView = new ModelAndView("addTask");
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("personId", personId);
        return modelAndView;

    }

    /**
     *
     * @param taskName taskName
     * @param startDate startDate
     * @param personId personId
     * @return TASK_LIST
     */
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public final String launchAddForm(
            @RequestParam("taskName") final String taskName,
            @RequestParam("startDate") final String startDate,
            @RequestParam("personId") final Long personId) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Inserting task with Name : " + taskName
                    + ", StartDate : " + startDate
                    + ", PersonId : " + personId);
        }

        DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
        DateTime dateTime = formatter.parseDateTime(startDate);
        Task task = new Task();
        task.setTaskName(taskName);
        task.setStartDate(dateTime);
        task.setTaskState(true);
        taskService.addTask(task, personId);

        return TASK_LIST;

    }

    /**
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/getTasks", method = RequestMethod.GET)
    public final ModelAndView getTaskListView() {

        List<Task> tasks = taskService.getTasks();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("taskList.size = " + tasks.size());
        }

        return new ModelAndView("taskList", "tasks", tasks);

    }

    /**
     *
     * @param personId personId
     * @return ModelAndView
     */
    @RequestMapping(value = "/getTasksById", method = RequestMethod.GET)
    public final ModelAndView getTaskListByIdView(
            @RequestParam("id") final Long personId) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("personId = " + personId);
        }

        List<Task> tasks;
        try {
            tasks = taskService.getTasksById(personId);
        } catch (IllegalArgumentException e) {
            tasks = taskService.getTasks();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("taskList.size = " + tasks.size());
        }

        return new ModelAndView("taskList", "tasks", tasks);

    }

    /**
     *
     * @param taskId taskId
     * @return ModelAndView
     */
    @RequestMapping(value = "/getTaskById", method = RequestMethod.GET)
    public final ModelAndView getTaskByIdView(
            @RequestParam("id") final Long taskId) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("taskId: " + taskId);
        }

        List<Task> tasks;
        try {
            Task taskFromDb = taskService.getTaskById(taskId);
            tasks = new ArrayList<>(1);
            tasks.add(taskFromDb);
        } catch (IllegalArgumentException e) {
            tasks = taskService.getTasks();
        }

        return new ModelAndView("taskList", "tasks", tasks);

    }

    /**
     *
     * @param taskId taskId
     * @param taskName taskName
     * @param startDate startDate
     * @return TASK_LIST
     */
    @RequestMapping(value = "/updateTask", method = {
            RequestMethod.PUT,
            RequestMethod.POST
    })
    public final String launchUpdateForm(
            @RequestParam("id") final Long taskId,
            @RequestParam("taskName") final String taskName,
            @RequestParam("startDate") final String startDate) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("TaskId : " + taskId
                    + ", TaskName : " + taskName
                    + ", StartDate : " + startDate);
        }

        DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
        DateTime dtStart = formatter.parseDateTime(startDate);
        DateTime dtEnd = new DateTime();
        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setStartDate(dtStart);
        task.setEndDate(dtEnd);
        task.setElapsedTime(new Period(
                dtStart, dtEnd, PeriodType.minutes()).getMinutes());
        task.setTaskState(false);
        taskService.updateTask(task);

        return TASK_LIST;

    }

    /**
     *
     * @param taskId taskId
     * @return TASK_LIST
     */
    @RequestMapping(value = "/deleteTask", method = {
            RequestMethod.DELETE,
            RequestMethod.POST
    })
    public final String removeTask(@RequestParam("id") final Long taskId) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Removing task with id = " + taskId);
        }

        taskService.removeTask(taskId);

        return TASK_LIST;

    }

}
