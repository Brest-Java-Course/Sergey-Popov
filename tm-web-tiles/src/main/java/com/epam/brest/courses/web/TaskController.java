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

    private static final String TASK_LIST = "redirect:/mvc/task/getTasks";

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TaskService taskService;

    @RequestMapping
    public String init() {

        return TASK_LIST;

    }

    @RequestMapping(value="/addTask", method= RequestMethod.GET)
    public ModelAndView getAddFormView(@RequestParam("personId")Long personId) {

        ModelAndView modelAndView = new ModelAndView("addTask");
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("personId", personId);
        return modelAndView;

    }

    @RequestMapping(value="/addTask", method= RequestMethod.POST)
    public String launchAddForm(@RequestParam("taskName")String taskName,
                                @RequestParam("startDate")String startDate,
                                @RequestParam("personId")Long personId) {

        LOGGER.debug("Inserting task with Name : " + taskName +
                ", StartDate : " + startDate + ", PersonId : " + personId);

        DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
        DateTime dt = formatter.parseDateTime(startDate);
        Task task = new Task();
        task.setTaskName(taskName);
        task.setStartDate(dt);
        task.setTaskState(true);
        taskService.addTask(task, personId);

        return TASK_LIST;

    }

    @RequestMapping(value="/getTasks", method= RequestMethod.GET)
    public ModelAndView getTaskListView() {

        List<Task> tasks = taskService.getTasks();

        LOGGER.debug("taskList.size = " + tasks.size());

        return new ModelAndView("taskList", "tasks", tasks);

    }

    @RequestMapping(value="/getTasksById", method= RequestMethod.GET)
    public ModelAndView getTaskListByIdView(@RequestParam("id")Long personId) {

        LOGGER.debug("personId = " + personId);

        List<Task> tasks;
        try {
            tasks = taskService.getTasksById(personId);
        } catch (IllegalArgumentException e) {
            tasks = taskService.getTasks();
        }

        LOGGER.debug("taskList.size = " + tasks.size());

        return new ModelAndView("taskList", "tasks", tasks);

    }

    @RequestMapping(value = "/getTaskById", method = RequestMethod.GET)
    public ModelAndView getTaskByIdView(@RequestParam("id")Long taskId) {

        LOGGER.debug("taskId: " + taskId);

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

    @RequestMapping(value="/updateTask", method= {RequestMethod.PUT, RequestMethod.POST})
    public String launchUpdateForm(@RequestParam("id")Long taskId,
                                   @RequestParam("taskName")String taskName,
                                   @RequestParam("startDate")String startDate) {

        LOGGER.debug("TaskId : " + taskId +
                ", TaskName : " + taskName +
                ", StartDate : " + startDate);

        DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
        DateTime dtStart = formatter.parseDateTime(startDate);
        DateTime dtEnd = new DateTime();
        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setStartDate(dtStart);
        task.setEndDate(dtEnd);
        task.setElapsedTime(new Period(dtStart, dtEnd, PeriodType.minutes()).getMinutes());
        task.setTaskState(false);
        taskService.updateTask(task);

        return TASK_LIST;

    }

    @RequestMapping(value = "/deleteTask", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String removeTask(@RequestParam("id")Long taskId) {

        LOGGER.debug("Removing task with id = " + taskId);

        taskService.removeTask(taskId);

        return TASK_LIST;

    }

}