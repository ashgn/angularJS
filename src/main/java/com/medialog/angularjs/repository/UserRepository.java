package com.medialog.angularjs.repository;

import com.medialog.angularjs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by ASH on 2016-05-03.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByName(String name);

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneByEmail(String email);
}
