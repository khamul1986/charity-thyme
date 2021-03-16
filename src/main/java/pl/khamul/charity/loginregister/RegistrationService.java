package pl.khamul.charity.loginregister;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.khamul.charity.exception.UserAlreadyExistsException;
import pl.khamul.charity.user.User;
import pl.khamul.charity.user.UserRepository;

import javax.transaction.Transactional;

@Service
public class RegistrationService implements RegistrationServiceInterface {


    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;


    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(User user)
            throws UserAlreadyExistsException {

        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Ten adres jest juz w bazie"
                            + user.getEmail());
        }

        String encodedPass = encoder.encode(user.getPassword());

        user.setPassword(encodedPass);
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

}
