package com.ddf.training.springsecurity.security;

import com.ddf.training.springsecurity.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.ddf.training.springsecurity.enums.UserPermission.STUDENT_WRITE;
import static com.ddf.training.springsecurity.enums.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/home", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                //.antMatchers(HttpMethod.DELETE, "/management/**").hasAuthority(STUDENT_WRITE.getPermission())
                //.antMatchers(HttpMethod.POST, "/management/**").hasAuthority(STUDENT_WRITE.getPermission())
                //.antMatchers(HttpMethod.PUT, "/management/**").hasAuthority(STUDENT_WRITE.getPermission())
                //.antMatchers(HttpMethod.GET, "/management/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/courses", true)
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                    .key("securedkey")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //Remove this when CSRF  is enabled.
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login")
        ;
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dany = User.builder()
                .username("dany")
                .password(passwordEncoder.encode("dany"))
                //.roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(STUDENT.getAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                //.roles(ADMIN.name()) //ROLE_ADMIN
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails adminTrainee = User.builder()
                .username("admin2")
                .password(passwordEncoder.encode("admin"))
                //.roles(ADMIN_TRAINEE.name()) //ROLE_ADMIN
                .authorities(ADMIN_TRAINEE.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                dany,
                admin,
                adminTrainee
        );
    }
}
