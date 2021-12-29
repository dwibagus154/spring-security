package com.dwibagus.springsecurity.controller;


import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.LoginRequest;
import com.dwibagus.springsecurity.payload.UserResponse;
import com.dwibagus.springsecurity.payload.UsernamePassword;
import com.dwibagus.springsecurity.response.CommonResponse;
import com.dwibagus.springsecurity.response.CommonResponseGenerator;
import com.dwibagus.springsecurity.service.AdminService;
import com.dwibagus.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> register(@RequestBody UsernamePassword req) {
        try{
            UserResponse userResponse = authService.register(req);
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "success register", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, e.getMessage() ,"403"),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/member/login")
    public ResponseEntity<?> loginMember(@RequestBody LoginRequest req) {
        try {
            if (authService.loginMember(req) == null){
                return new ResponseEntity<>(commonResponseGenerator.response(null, "Password didnt match" ,"403"),HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(commonResponseGenerator.response(authService.loginMember(req), "success login", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, e.getMessage() ,"403"),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.generateToken(req));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> userActivated(@PathVariable Long id) {
        try {
            UserResponse userResponse = adminService.userActivate(id);
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "user activated", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, e.getMessage() ,"403"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUser(){
        try {
            return ResponseEntity.ok(commonResponseGenerator.response(adminService.getAllUser(), "get all user success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user", "400"),HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        System.out.println(id);
        try{
            UserResponse userResponse =  adminService.getUser(id);
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "get user success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user with id " + id, "400"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/vo/user/{id}")
    public ResponseEntity<?> getUserVO(@PathVariable Long id){
        System.out.println(id);
        try{
            UserResponse userResponse =  adminService.getUser(id);
            return ResponseEntity.ok(userResponse);
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user with id " + id, "400"),HttpStatus.BAD_REQUEST);
        }
    }

}
