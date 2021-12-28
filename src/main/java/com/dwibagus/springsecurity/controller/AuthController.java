package com.dwibagus.springsecurity.controller;


import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.UsernamePassword;
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


    @GetMapping
    public String listUsers(){
        System.out.println("users");
        return "this is list users";
    }


    @PostMapping("/register")
    public User register(@RequestBody UsernamePassword req) {
        User user = authService.register(req);
        return user;
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UsernamePassword req) {
        return ResponseEntity.ok(authService.generateToken(req));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> userActivated(@PathVariable Long id) {
        System.out.println("contoh");
        System.out.println(id);
        adminService.userActivate(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public List<User> getAllUser(){
        return adminService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        System.out.println(id);
        User user =  adminService.getUser(id);
        return ResponseEntity.  ok(user);
    }

}
