package com.osu.venglar.EventHarbor.repository;

import com.osu.venglar.EventHarbor.auth.AuthenticationResponse;
import com.osu.venglar.EventHarbor.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Was empty - this method is for the authentication
    Optional<User> findByEmail(String email);

    User findByUserId(Long id);

    @Transactional
    Integer deleteByUserId(Long id);

}
