package com.dwibagus.springsecurity.service.impl;

import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.repository.UserRepository;
import com.dwibagus.springsecurity.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;


    @Override
    public void userActivate(Long id){
        User user = userRepository.getById(id);
        System.out.println(user.getUsername());
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
