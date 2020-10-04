package com.ddf.training.springsecurity.jwt;

import lombok.Value;

@Value
class UsernameAndPasswordAuthenticationRequest {

    String username;
    String password;
}
