package com.dwibagus.springsecurity.controller;


import com.dwibagus.springsecurity.model.User;
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
    public CommonResponse<User> register(@RequestBody UsernamePassword req) {
        try{
            User user = authService.register(req);
            return commonResponseGenerator.response(user, "success register", "200");
        }catch (Exception e){
            return commonResponseGenerator.response(null, e.getMessage() ,"403");
        }
    }

//    @PostMapping("/member/login")
//    public ResponseEntity<?> loginMember(@RequestBody UsernamePassword req) {
//        return ResponseEntity.ok(authService.loginMember(req));
//    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UsernamePassword req) {
        return ResponseEntity.ok(authService.generateToken(req));
    }

    @PutMapping("/user/{id}")
    public CommonResponse<User> userActivated(@PathVariable Long id) {
        System.out.println("contoh");
        System.out.println(id);
        User user = adminService.userActivate(id);
        return commonResponseGenerator.response(user, "user activated", "200");
//        try {
//            User user = adminService.userActivate(id);
//            return commonResponseGenerator.response(user, "user activated", "200");
//        }catch (Exception e){
//            return commonResponseGenerator.response(null, e.getMessage() ,"403");
//        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUser(){
        try {
            return ResponseEntity.ok(commonResponseGenerator.response(adminService.getAllUser(), "get all user success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
//    @GetMapping("/user")
//    public ResponseEntity<List<User>> getAllUser(){
//        return ResponseEntity.ok(new Response(adminService.getAllUser()));
////        return commonResponseGenerator.response(adminService.getAllUser(), "get all user success", "200");
//    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        System.out.println(id);
        try{
            User user =  adminService.getUser(id);
            return ResponseEntity.ok(commonResponseGenerator.response(user, "get user success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user with id " + id, "400"),HttpStatus.BAD_REQUEST);
        }
    }

}
