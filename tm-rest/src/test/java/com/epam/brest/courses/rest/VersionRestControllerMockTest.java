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
public class VersionRestControllerMockTest {

    private MockMvc mockMvc;

    @Resource
    private VersionRestController versionRestController;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = standaloneSetup(versionRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @Test
    public void testGetVersion() throws Exception {

        this.mockMvc.perform(
                get("/rest/version", "Empty")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("\"1.0\""));

    }
}