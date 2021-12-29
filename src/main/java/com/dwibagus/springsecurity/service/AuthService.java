package com.dwibagus.springsecurity.service;

import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.LoginRequest;
import com.dwibagus.springsecurity.payload.TokenResponse;
import com.dwibagus.springsecurity.payload.UserResponse;
import com.dwibagus.springsecurity.payload.UsernamePassword;

public interface AuthService {
    UserResponse createResponse(User user);
    UserResponse register(UsernamePassword req);
    TokenResponse generateToken(LoginRequest req);

    TokenResponse loginMember(LoginRequest req);
}
