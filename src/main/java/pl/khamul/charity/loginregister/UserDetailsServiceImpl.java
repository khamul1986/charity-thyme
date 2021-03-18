package pl.khamul.charity.loginregister;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.charity.user.User;
import pl.khamul.charity.user.UserRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findFirstByEmail(email).get();

        if (user != null) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            user.getRole().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }else{
            throw new UsernameNotFoundException("User using email" + email+ " not found!");
        }
    }

}
