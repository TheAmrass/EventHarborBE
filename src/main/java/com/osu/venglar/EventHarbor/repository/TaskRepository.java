package com.osu.venglar.EventHarbor.repository;
import com.osu.venglar.EventHarbor.model.Task;
import com.osu.venglar.EventHarbor.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    /*@Query(value = """
      select t from Task t inner join User u\s
      on t.createdBy.userId = u.userId\s
      where u.userId = :id\s
      """)*/

    List<Task> findAllByUsers(List<User> users);


    Task getByTaskId(Long id);

    @Transactional
    Integer deleteByTaskId(Long id);
}
