package com.medialog.angularjs.web.rest.errors;

import java.io.Serializable;

/**
 * Created by ASH on 2016-05-03.
 */
public class ParameterizedErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String message;
    private final String[] params;

    public ParameterizedErrorDTO(String message, String... params) {
        this.message = message;
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public String[] getParams() {
        return params;
    }

}
