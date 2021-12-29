package com.dwibagus.springsecurity.service.impl;

import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.UserResponse;
import com.dwibagus.springsecurity.repository.UserRepository;
import com.dwibagus.springsecurity.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setIsAdmin(user.getIsAdmin());
        userResponse.setActive(user.isActive());
        userResponse.setCreated_at(user.getCreated_at());
        userResponse.setUpdated_at(user.getUpdated_at());
        return userResponse;
    }

    @Override
    public UserResponse userActivate(Long id){
        User user = userRepository.findById(id).get();
        System.out.println(user.getUsername());
        user.setActive(true);
        userRepository.save(user);

//        create user response
        UserResponse userResponse = this.createResponse(user);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUser(){
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        UserResponse userResponse = new UserResponse();
        for (int i = 0; i < userList.size(); i++){
            userResponse = this.createResponse(userList.get(i));
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    public UserResponse getUser(Long id){
        User user = userRepository.findById(id).get();
        System.out.println(user.getUsername());
        //        create user response
        UserResponse userResponse = this.createResponse(user);
        return userResponse;
    }
}
