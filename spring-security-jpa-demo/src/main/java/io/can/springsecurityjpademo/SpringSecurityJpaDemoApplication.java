package io.can.springsecurityjpademo;

import io.can.springsecurityjpademo.model.User;
import io.can.springsecurityjpademo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityJpaDemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("can");
        user.setPassword("123456");
        user.setActive(true);
        user.setRoles("ROLE_USER,ROLE_ADMIN");
        userRepository.save(user);
    }
}
