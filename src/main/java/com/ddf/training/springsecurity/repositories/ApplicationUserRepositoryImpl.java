package com.ddf.training.springsecurity.repositories;

import com.ddf.training.springsecurity.domain.ApplicationUser;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ddf.training.springsecurity.enums.UserRole.*;

@Repository("fake")
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

    private final PasswordEncoder passwordEncoder;

    public ApplicationUserRepositoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        return Lists.newArrayList(
                new ApplicationUser(STUDENT.getAuthorities(), "dany", passwordEncoder.encode("dany"), true, true, true, true),
                new ApplicationUser(STUDENT.getAuthorities(), "brianna", passwordEncoder.encode("brianna"), true, true, true, true),
                new ApplicationUser(ADMIN.getAuthorities(), "admin", passwordEncoder.encode("admin"), true, true, true, true),
                new ApplicationUser(ADMIN_TRAINEE.getAuthorities(), "admin2", passwordEncoder.encode("admin"), true, true, true, true)
        );
    }
}
