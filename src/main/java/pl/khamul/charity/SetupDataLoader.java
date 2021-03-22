package pl.khamul.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.charity.role.Role;
import pl.khamul.charity.role.RoleRepository;
import pl.khamul.charity.user.User;
import pl.khamul.charity.user.UserRepository;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        final Role userRole = createRoleIfNotFound("ROLE_USER");

        Set <Role> admin = new HashSet<>();
        admin.add(adminRole);
        admin.add(userRole);
        createUserIfNotFound("admin@test.pl", "Admin", "admin", admin);

        alreadySetup = true;
    }


    @Transactional
    Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
        }
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String email, final String userName, final String password, final Set<Role> roles) {
        User user = new User();
        if (userRepository.findFirstByEmail(email).isPresent()) {

            user = userRepository.findFirstByEmail(email).get();
            user.setUserName(userName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setRole(roles);
            user = userRepository.save(user);
        }

        return user;
    }

}
