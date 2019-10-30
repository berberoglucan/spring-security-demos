package io.can.springsecurityjpademo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // UserDetailsService -> Authentication Manager'in cagirdagi, user'i authenticate
    // etmesi icin kullanilan bir servistir. Istedigimiz business'a gore kendimiz implement
    // etmeliyiz.

    // UserDetailsService sadece JPA ile konusmayabilir. Kullanici bilgileri istenirse
    // bir text dosyasindan, hatta hard-coded olarak bile verilebilir.
    // Normalde sadece authentication manager'a bu servisi calistir diyoruz ve geriye UserDetails
    // objesi donduruyoruz.

    @Autowired
    private UserDetailsService userDetailsService;

    // authentication manager builder uses for configure the authentication
    // get user data from jpa
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // authentication manager'in UserDetailsService kullanmasini istiyoruz.
        // istenirse InMemoryAuth, JdbcAuth veya LDAPAuth, UserDetailsService yerine kullanilabilir.

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // http authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
