package com.medialog.angularjs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by ASH on 2016-05-03.
 */
@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 테이블 유니크키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 사용자 ID
     */
    @NotNull
    @Pattern(regexp = "^[a-z0-9]*$|(anonymousUser)")
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    /**
     * 사용자 PW
     */
    @JsonIgnore
    @NotNull
    @Size(min = 6, max = 60)
    @Column(name = "password", length = 60)
    private String password;

    /**
     * 사용자 명
     */
    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 사용자 EMAIL
     */
    @NotNull
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

}
