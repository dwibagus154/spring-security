package com.dwibagus.springsecurity.service;

import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.UserResponse;

import java.util.List;

public interface AdminService {

    UserResponse createResponse(User user);
    UserResponse userActivate(Long id);
    List<UserResponse> getAllUser();

    UserResponse getUser(Long id);
}
