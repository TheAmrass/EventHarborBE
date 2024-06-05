package com.osu.venglar.EventHarbor.controller;

import com.osu.venglar.EventHarbor.model.Project;
import com.osu.venglar.EventHarbor.model.Task;
import com.osu.venglar.EventHarbor.model.User;
import com.osu.venglar.EventHarbor.repository.ProjectRepository;
import com.osu.venglar.EventHarbor.requests.AddProjectRequest;
import com.osu.venglar.EventHarbor.requests.UpdateTaskRequest;
import com.osu.venglar.EventHarbor.services.ProjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/api/v1/project/add/{userId}")
    public ResponseEntity<String> addProject(
            @RequestBody AddProjectRequest request,
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(service.addProject(request, userId));
    }

    @GetMapping("/api/v1/project/user/{userId}")
   List <Project> projectsForUser(@PathVariable Long userId){
        return service.getProjectsForUser(userId);
    }

    @GetMapping("/api/v1/project/{projectId}")
    Project projectFromDb(@PathVariable Long projectId){return projectRepository.findByProjectId(projectId);}

    @DeleteMapping("/api/v1/project/{deleteId}")
    Integer deleteProject(@PathVariable Long deleteId){return projectRepository.deleteByProjectId(deleteId);}

    @PutMapping("/api/v1/project/{id}")
    public String updateProject(
            @RequestBody AddProjectRequest addProjectRequest, @PathVariable Long id
    ){
        return service.update(addProjectRequest, id);
    }



}
