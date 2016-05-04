package com.medialog.angularjs.web.rest;

import com.medialog.angularjs.domain.User;
import com.medialog.angularjs.repository.UserRepository;
import com.medialog.angularjs.service.UserService;
import com.medialog.angularjs.web.rest.dto.UserDTO;
import com.medialog.angularjs.web.util.HeaderUtil;
import com.medialog.angularjs.web.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ASH on 2016-05-02.
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        if (userRepository.findOneByLogin(userDTO.getLogin()).isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("userManagement", "userexists", "Login already in use"))
                    .body(null);
        } else if (userRepository.findOneByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("userManagement", "emailexists", "Email already in use"))
                    .body(null);
        } else {
            User newUser = userService.createUser(userDTO);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                    .headers(HeaderUtil.createAlert("A user is created with identifier " + newUser.getLogin(), newUser.getLogin()))
                    .body(newUser);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) throws URISyntaxException {
        Page<User> page = userRepository.findAll(pageable);
        List<UserDTO> userDTOs = page.getContent().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(userDTOs, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{login:[_'.@a-z0-9-]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);
        return userService.getUserWithAuthoritiesByLogin(login)
                .map(UserDTO::new)
                .map(sampleDTO -> new ResponseEntity<>(sampleDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/users/{login:[_'.@a-z0-9-]+}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);
        userService.deleteUserInformation(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("A user is deleted with identifier " + login, login)).build();
    }

}
