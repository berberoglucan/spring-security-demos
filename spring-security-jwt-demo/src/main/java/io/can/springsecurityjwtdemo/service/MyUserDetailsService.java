package io.can.springsecurityjwtdemo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    // call by spring security authenticate method
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // return hard-coded user
        return User.withUsername("can")
                .password("123456")
                .authorities(new ArrayList<>())
                .build();
    }
}
