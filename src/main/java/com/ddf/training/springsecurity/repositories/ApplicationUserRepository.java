package com.ddf.training.springsecurity.repositories;

import com.ddf.training.springsecurity.domain.ApplicationUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
