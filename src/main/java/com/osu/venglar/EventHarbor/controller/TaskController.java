package com.osu.venglar.EventHarbor.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osu.venglar.EventHarbor.auth.AuthenticationResponse;
import com.osu.venglar.EventHarbor.auth.RegisterRequest;
import com.osu.venglar.EventHarbor.auth.UpdateRequest;
import com.osu.venglar.EventHarbor.model.Task;
import com.osu.venglar.EventHarbor.repository.TaskRepository;
import com.osu.venglar.EventHarbor.requests.AddTaskRequest;
import com.osu.venglar.EventHarbor.requests.UpdateTaskRequest;
import com.osu.venglar.EventHarbor.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;
    
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/api/v1/task/add/{userId}")
    public ResponseEntity<String> addTask(
            @RequestBody AddTaskRequest request,
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(service.addTask(request, userId));
    }

    @GetMapping("/api/v1/tasks/{userId}")
    List<Task> tasksForUser(@PathVariable Long userId){return taskRepository.findAllTaskByUser(userId);}

    //TODO AssignedTasks - tasks that were assigned to the user

    @GetMapping("/api/v1/task/{taskId}")
    Task taskFromDb(@PathVariable Long taskId){return taskRepository.findByTaskId(taskId);}

    @DeleteMapping("/api/v1/task/{id}")
    Integer deleteTask(@PathVariable Long id){return taskRepository.deleteByTaskId(id);}

    @PutMapping("/api/v1/task/{id}")
    public String updateTask(
            @RequestBody UpdateTaskRequest updateTaskRequest, @PathVariable Long id
    ){
        return service.update(updateTaskRequest, id);
    }

    @PutMapping("/api/v1/task/complete/{id}")
    public String completeTask(
            @PathVariable Long id
    ){
        return service.complete(id);
    }


}
