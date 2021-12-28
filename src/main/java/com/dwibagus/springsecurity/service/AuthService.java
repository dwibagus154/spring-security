package com.dwibagus.springsecurity.service;

import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.TokenResponse;
import com.dwibagus.springsecurity.payload.UsernamePassword;

public interface AuthService {
    User register(UsernamePassword req);
    TokenResponse generateToken(UsernamePassword req);

//    User loginMember(UsernamePassword req);
}
