package com.epam.brest.courses.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-rest-mock-test.xml"})
public class HelloRestControllerMockTest {

    private MockMvc mockMvc;

    @Resource
    private HelloRestController helloRestController;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = standaloneSetup(helloRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @Test
    public void testGetDescription() throws Exception {

        this.mockMvc.perform(
                get("/rest/hello", "Empty")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("\"Task Manager is a simple sql-based application\""));

    }

}