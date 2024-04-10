package com.osu.venglar.EventHarbor.services;

import com.osu.venglar.EventHarbor.model.Task;
import com.osu.venglar.EventHarbor.repository.TaskRepository;
import com.osu.venglar.EventHarbor.repository.UserRepository;
import com.osu.venglar.EventHarbor.requests.AddTaskRequest;
import com.osu.venglar.EventHarbor.requests.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final UserRepository userRepository;
    public String addTask(AddTaskRequest request, Long userId) {
        var task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .user(userRepository.findByUserId(userId))
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .build();
        repository.save(task);
        return "Úkol byl úspěšně přidán.";
    }

    public String update(UpdateTaskRequest request, Long id) {
        Task taskToUpdate = repository.findByTaskId(id);
        System.out.println(taskToUpdate);
        try {
            taskToUpdate.setCompleted(request.getCompleted());
            taskToUpdate.setDescription(request.getDescription());
            taskToUpdate.setPriority(request.getPriority());
            taskToUpdate.setTitle(request.getTitle());
            taskToUpdate.setDueDate(request.getDueDate());
            repository.save(taskToUpdate);
            return "Task s id " + id + "byl úspěšně aktualizován.";
        } catch (Exception e){
            e.printStackTrace();
            return "Chyba při aktualizaci tasku";
        }
    }

    public String complete(Long id) {
        Task taskToComplete = repository.findByTaskId(id);
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
