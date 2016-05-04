package com.medialog.angularjs.service;

import com.medialog.angularjs.domain.User;
import com.medialog.angularjs.repository.UserRepository;
import com.medialog.angularjs.web.rest.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by ASH on 2016-05-03.
 */
@Slf4j
@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setActivated(true);
        userRepository.save(user);
        log.debug("Created Information for Sample: {}", user);
        return user;
    }

//    public void updateUserInformation(String name, String email) {
//        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
//            u.setName(name);
//            u.setEmail(email);
//            userRepository.save(u);
//            log.debug("Changed Information for Sample: {}", u);
//        });
//    }

    public void deleteUserInformation(String login) {
        userRepository.findOneByLogin(login).ifPresent(u -> {
            userRepository.delete(u);
            log.debug("Deleted User: {}", u);
        });
    }

}
