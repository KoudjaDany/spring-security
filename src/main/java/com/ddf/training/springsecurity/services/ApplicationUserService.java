package com.ddf.training.springsecurity.services;

import com.ddf.training.springsecurity.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserService(@Qualifier("fake") ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository.selectApplicationUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("The user of username %s doesn't exist.", username)));
    }
}
