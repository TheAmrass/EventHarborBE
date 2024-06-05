package com.osu.venglar.EventHarbor.services;

import com.osu.venglar.EventHarbor.model.Task;
import com.osu.venglar.EventHarbor.model.User;
import com.osu.venglar.EventHarbor.repository.ProjectRepository;
import com.osu.venglar.EventHarbor.repository.TaskRepository;
import com.osu.venglar.EventHarbor.repository.UserRepository;
import com.osu.venglar.EventHarbor.requests.AddTaskRequest;
import com.osu.venglar.EventHarbor.requests.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    public String addTask(AddTaskRequest request, Long userId) {
        List<User> assignedTo = userRepository.findAllById(request.getUserIds());
        var task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdBy(userRepository.findByUserId(userId))
                .users(assignedTo)
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .project(projectRepository.findByProjectId(request.getProjectId()))
                .build();
        repository.save(task);
        return "Úkol byl úspěšně přidán.";
    }

    public List<Task> getTasksForUser(Long userId){
        User user = userRepository.findByUserId(userId);
        List<User> userList = Collections.singletonList(user);
        return repository.findAllByUsers(userList);
    }

    public String update(UpdateTaskRequest request, Long id) {
        Task taskToUpdate = repository.getByTaskId(id);
        List<User> assignedTo = userRepository.findAllById(request.getUserIds());
        System.out.println(taskToUpdate);
        try {
            taskToUpdate.setCompleted(request.getCompleted());
            taskToUpdate.setDescription(request.getDescription());
            taskToUpdate.setPriority(request.getPriority());
            taskToUpdate.setUsers(assignedTo);
            taskToUpdate.setTitle(request.getTitle());
            taskToUpdate.setDueDate(request.getDueDate());
            taskToUpdate.setProject(projectRepository.findByProjectId(request.getProjectId()));
            repository.save(taskToUpdate);
            return "Task s id " + id + "byl úspěšně aktualizován.";
        } catch (Exception e){
            e.printStackTrace();
            return "Chyba při aktualizaci tasku";
        }
    }

    public String complete(Long id) {
        Task taskToComplete = repository.getByTaskId(id);
        if (taskToComplete.getCompleted() == 0)
            taskToComplete.setCompleted(1);
        else
            taskToComplete.setCompleted(0);
        System.out.println(taskToComplete);
        try {

            repository.save(taskToComplete);
            return "Task s id " + id + "byl úspěšně aktualizován.";
        } catch (Exception e){
            e.printStackTrace();
            return "Chyba při aktualizaci tasku";
        }
    }
}
