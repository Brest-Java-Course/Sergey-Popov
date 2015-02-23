package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Task;
import com.epam.brest.courses.rest.datafixture.TaskDataFixture;
import com.epam.brest.courses.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-rest-mock-test.xml"})
public class TaskRestControllerMockTest {

    /**
     *
     */
    private MockMvc mockMvc;

    /**
     *
     */
    @Resource
    private TaskRestController taskRestController;

    /**
     *
     */
    @Autowired
    private TaskService taskService;

    /**
     *
     */
    @Before
    public void setUp() {

        this.mockMvc = standaloneSetup(taskRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

        reset(taskService);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testAddTask() throws Exception {

        Task taskFromDb = TaskDataFixture.getExistTaskSame(1l);
        expect(taskService.addTask(anyObject(Task.class), anyLong())).andReturn(taskFromDb);
        replay(taskService);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskJSON = objectMapper.writeValueAsString(TaskDataFixture.getNewTask());

        this.mockMvc.perform(
                post("/rest/tasks")
                        .content(taskJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"taskId\":1,\"taskName\":\"MockTaskName\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}"));

        verify(taskService);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetTasks() throws Exception {

        expect(taskService.getTasks()).andReturn(TaskDataFixture.getSampleTaskList());
        replay(taskService);

        this.mockMvc.perform(
                get("/rest/tasks")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"taskId\":1,\"taskName\":\"MockTaskName1\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}," +
                        "{\"taskId\":2,\"taskName\":\"MockTaskName2\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}," +
                        "{\"taskId\":3,\"taskName\":\"MockTaskName3\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}]"));

        verify(taskService);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetTasksById() throws Exception {

        expect(taskService.getTasksById(1l)).andReturn(TaskDataFixture.getSampleTaskList());
        replay(taskService);

        this.mockMvc.perform(
                get("/rest/tasks/person/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"taskId\":1,\"taskName\":\"MockTaskName1\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}," +
                        "{\"taskId\":2,\"taskName\":\"MockTaskName2\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}," +
                        "{\"taskId\":3,\"taskName\":\"MockTaskName3\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}]"));

        verify(taskService);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetTaskById() throws Exception {

        expect(taskService.getTaskById(1l)).andReturn(TaskDataFixture.getExistTask(1l));
        replay(taskService);

        this.mockMvc.perform(
                get("/rest/tasks/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"taskId\":1,\"taskName\":\"MockTaskName1\",\"taskState\":true,\"startDate\":\"2014-11-22T17:26:00.000+03:00\",\"endDate\":null,\"elapsedTime\":null,\"person\":null}"));

        verify(taskService);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testUpdateTask() throws Exception {

        taskService.updateTask(anyObject(Task.class));
        replay(taskService);

        ObjectMapper objectMapper = new ObjectMapper();
        Task task = TaskDataFixture.getExistTask(1l);
        task.setTaskName("RestMockTestTaskUpdated");
        String personJSON = objectMapper.writeValueAsString(task);

        ResultActions result = this.mockMvc.perform(
                put("/rest/tasks")
                        .content(personJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print());
        result.andExpect(status().isOk());

        verify(taskService);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testRemovePerson() throws Exception {

        taskService.removeTask(1l);
        replay(taskService);

        ResultActions result = this.mockMvc.perform(
                delete("/rest/tasks/1")
        )
                .andDo(print());
        result.andExpect(status().isOk());

        verify(taskService);

    }

}