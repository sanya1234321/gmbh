package hh.gmbh.security.jwt;

import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;

public class InvalidJwtAuthenticationException extends AuthenticationException implements Serializable {

    private static final long serialVersionUID = -7190981329334244535L;

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
