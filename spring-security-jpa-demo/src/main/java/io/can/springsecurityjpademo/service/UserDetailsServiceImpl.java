package io.can.springsecurityjpademo.service;

import io.can.springsecurityjpademo.model.HardCodedUserDetails;
import io.can.springsecurityjpademo.model.MyUserDetails;
import io.can.springsecurityjpademo.model.User;
import io.can.springsecurityjpademo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    // user ile ilgili business logic burada yapiliyor. Bu business'ta JPA ile ilgili işlemler yapılmalı
    // ve geriye UserDetails turunde obje donulmelidir

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // return new HardCodedUserDetails(s);

        Optional<User> optionalUser = userRepository.findByUserName(userName);
        User user = optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("Username is not found for given : " + userName));
        return new MyUserDetails(user);

        // istenirse spring security'nin kendi User class'ida kullanilabilir
        /*return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                .password(user.getPassword())
                .disabled(!user.isActive())
                .authorities(user.getRoles().split(","))
                .build();*/
    }

}
