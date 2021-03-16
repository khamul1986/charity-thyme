package pl.khamul.charity.loginregister;


import pl.khamul.charity.exception.UserAlreadyExistsException;
import pl.khamul.charity.user.User;

public interface RegistrationServiceInterface {

    User registerNewUserAccount(User user) throws UserAlreadyExistsException;
}
