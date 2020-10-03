package com.ddf.training.springsecurity.security;

import com.ddf.training.springsecurity.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.ddf.training.springsecurity.enums.UserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/home", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers("/management/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dany = User.builder()
                .username("dany")
                .password(passwordEncoder.encode("dany"))
                .roles(STUDENT.name()) //ROLE_STUDENT
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN.name()) //ROLE_ADMIN
                .build();

        UserDetails adminTrainee = User.builder()
                .username("admin2")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN_TRAINEE.name()) //ROLE_ADMIN
                .build();

        return new InMemoryUserDetailsManager(
                dany,
                admin,
                adminTrainee
        );
    }
}
