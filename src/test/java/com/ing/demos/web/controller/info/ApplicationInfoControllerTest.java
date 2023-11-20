package com.ing.demos.web.controller.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationInfoController.class)
class ApplicationInfoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BuildProperties buildProperties;

    @BeforeEach
    void setUp() {
        when(buildProperties.getVersion()).thenReturn("23.0.0");
        when(buildProperties.getTime()).thenReturn(Instant.now());
        when(buildProperties.getName()).thenReturn("store-demo");
    }

    @Test
    void whenGetApplicationInfo_WithUser_thenOk() throws Exception {
        mvc.perform(get("/").with(user("user"))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetApplicationInfo_WithoutUser_thenNotOk() throws Exception {
        mvc.perform(get("/").contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
