package com.medialog.angularjs.web.rest.dto;

import com.medialog.angularjs.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by ASH on 2016-05-03.
 */
@Data
public class UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    private Long id;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;


    @NotEmpty
    private String name;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    public UserDTO() {
    }

    public UserDTO(User user) {
    }
}
