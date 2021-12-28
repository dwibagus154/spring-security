package com.dwibagus.springsecurity.controller;


import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.UsernamePassword;
import com.dwibagus.springsecurity.response.CommonResponse;
import com.dwibagus.springsecurity.response.CommonResponseGenerator;
import com.dwibagus.springsecurity.service.AdminService;
import com.dwibagus.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor


public class AuthController {
    private final AuthService authService;
    private final AdminService adminService;

    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @GetMapping
    public String listUsers(){
        System.out.println("users");
        return "this is list users";
    }


    @PostMapping("/register")
    public CommonResponse<User> register(@RequestBody UsernamePassword req) {
        User user = authService.register(req);
        return commonResponseGenerator.successResponse(user, "success register");
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UsernamePassword req) {
        return ResponseEntity.ok(authService.generateToken(req));
    }

    @PutMapping("/user/{id}")
    public CommonResponse<User> userActivated(@PathVariable Long id) {
        System.out.println("contoh");
        System.out.println(id);
        User user = adminService.userActivate(id);
        return commonResponseGenerator.successResponse(user, "user activated");
    }

    @GetMapping("/user")
    public CommonResponse<List<User>> getAllUser(){
        return commonResponseGenerator.successResponse(adminService.getAllUser(), "get all user success");
    }

    @GetMapping("/user/{id}")
    public CommonResponse<User> getUser(@PathVariable Long id){
        System.out.println(id);
        User user =  adminService.getUser(id);
        return commonResponseGenerator.successResponse(user, "get user success");
    }

}
