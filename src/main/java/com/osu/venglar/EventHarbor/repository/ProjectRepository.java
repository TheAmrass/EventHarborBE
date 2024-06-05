package com.osu.venglar.EventHarbor.repository;

import com.osu.venglar.EventHarbor.model.Project;
import com.osu.venglar.EventHarbor.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectId(Long id);

    List<Project> findAllByUsers(List<User> users);

    @Transactional
    Integer deleteByProjectId(Long id);

}
