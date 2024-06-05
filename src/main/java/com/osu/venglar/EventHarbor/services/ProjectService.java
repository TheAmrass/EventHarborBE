package com.osu.venglar.EventHarbor.services;

import com.osu.venglar.EventHarbor.model.Project;
import com.osu.venglar.EventHarbor.model.Task;
import com.osu.venglar.EventHarbor.model.User;
import com.osu.venglar.EventHarbor.repository.ProjectRepository;
import com.osu.venglar.EventHarbor.repository.TaskRepository;
import com.osu.venglar.EventHarbor.repository.UserRepository;
import com.osu.venglar.EventHarbor.requests.AddProjectRequest;
import com.osu.venglar.EventHarbor.requests.AddTaskRequest;
import com.osu.venglar.EventHarbor.requests.UpdateProjectRequest;
import com.osu.venglar.EventHarbor.requests.UpdateTaskRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public String addProject(AddProjectRequest request, Long userId) {
        User creator = userRepository.findByUserId(userId);
        List<User> assignedTo = userRepository.findAllById(request.getUserIds());
        System.out.println("Assigned to:" + assignedTo);
        var project = Project.builder()
                .title(request.getTitle())
                .notes(request.getNotes())
                .users(assignedTo)
                .projectDate(request.getProjectDate())
                .createdBy(creator)
                .build();
        repository.save(project);
        return "Projekt byl úspěšně přidán.";
    }

    public List<Project> getProjectsForUser(Long userId){
        User user = userRepository.findByUserId(userId);
        List<User> userList = Collections.singletonList(user);
        return repository.findAllByUsers(userList);
    }

    public String update(AddProjectRequest request, Long id) {
        Project projectToUpdate = repository.findByProjectId(id);
        System.out.println(projectToUpdate);
        try {
            projectToUpdate.setTitle(request.getTitle());
            projectToUpdate.setProjectDate(request.getProjectDate());
            projectToUpdate.setUsers(userRepository.findAllById(request.getUserIds()));
            projectToUpdate.setNotes(request.getNotes());
            repository.save(projectToUpdate);
            return "Projekt s id " + id + "byl úspěšně aktualizován.";
        } catch (Exception e){
            e.printStackTrace();
            return "Chyba při aktualizaci projektu";
        }
    }

    //TODO assign
}
