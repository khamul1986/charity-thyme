package pl.khamul.charity.loginregister;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.khamul.charity.exception.UserAlreadyExistsException;
import pl.khamul.charity.role.Role;
import pl.khamul.charity.role.RoleRepository;
import pl.khamul.charity.user.User;
import pl.khamul.charity.user.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class RegistrationService implements RegistrationServiceInterface {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;


    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(User user)
            throws UserAlreadyExistsException {

        if (userRepository.findFirstByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "Ten adres jest juz w bazie"
                            + user.getEmail());
        }

        String encodedPass = encoder.encode(user.getPassword());


        user.setPassword(encodedPass);
        Role role =roleRepository.findByName("ROLE_USER");
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);

        return userRepository.save(user);
    }

}
