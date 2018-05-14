package com.activity.monitoring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.activity.monitoring.ApplicationTests;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotificationControllerTest extends ApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setupMockMvc() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void getEventsByEventIdTest() throws Exception {

        mvc.perform(get("/events?eventId=now&page=1&count=2"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print());
    }

}
