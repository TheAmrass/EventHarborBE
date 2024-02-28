package com.osu.venglar.EventHarbor.repository;

import com.osu.venglar.EventHarbor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
