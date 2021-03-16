package pl.khamul.charity.exception;

import javax.naming.AuthenticationException;

public class UserAlreadyExistsException extends AuthenticationException {

    public UserAlreadyExistsException(String explanation) {
        super(explanation);
    }
}
