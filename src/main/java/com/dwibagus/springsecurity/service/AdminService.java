package com.dwibagus.springsecurity.service;

import com.dwibagus.springsecurity.model.User;

import java.util.List;

public interface AdminService {
    void userActivate(Long id);
    List<User> getAllUser();

    User getUser(Long id);
}
