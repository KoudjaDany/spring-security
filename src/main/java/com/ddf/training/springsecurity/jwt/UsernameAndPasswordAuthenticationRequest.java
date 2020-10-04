package com.ddf.training.springsecurity.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@NoArgsConstructor
class UsernameAndPasswordAuthenticationRequest {

    String username;
    String password;
}
