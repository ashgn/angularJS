package com.medialog.angularjs.web.rest.errors;

/**
 * Created by ASH on 2016-05-03.
 */
public class CustomParameterizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String[] params;

    public CustomParameterizedException(String message, String... params) {
        super(message);
        this.message = message;
        this.params = params;
    }

    public ParameterizedErrorDTO getErrorDTO() {
        return new ParameterizedErrorDTO(message, params);
    }

}
