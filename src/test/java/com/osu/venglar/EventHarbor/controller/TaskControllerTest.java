package com.osu.venglar.EventHarbor.controller;

import com.osu.venglar.EventHarbor.model.Task;
import com.osu.venglar.EventHarbor.model.User;
import com.osu.venglar.EventHarbor.model.enums.Priority;
import com.osu.venglar.EventHarbor.repository.TaskRepository;
import com.osu.venglar.EventHarbor.requests.AddTaskRequest;
import com.osu.venglar.EventHarbor.services.TaskService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskControllerTest {

    @Test
    void addTask() {
    }

    @Test
    void tasksForUser() {
    }

    @Test
    void taskFromDb() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void completeTask() {
    }
}