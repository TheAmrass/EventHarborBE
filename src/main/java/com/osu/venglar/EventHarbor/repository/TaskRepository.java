package com.osu.venglar.EventHarbor.repository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osu.venglar.EventHarbor.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = """
      select t from Task t inner join User u\s
      on t.user.userId = u.userId\s
      where u.userId = :id\s
      """)

    List<Task> findAllTaskByUser(Long id);


    Task findByTaskId(Long id);

    @Transactional
    Integer deleteByTaskId(Long id);
}
