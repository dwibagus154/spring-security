package com.dwibagus.springsecurity.controller;


import com.dwibagus.springsecurity.payload.UsernamePassword;
import com.dwibagus.springsecurity.service.AdminService;
import com.dwibagus.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> register(@RequestBody UsernamePassword req) {
        authService.register(req);
        return ResponseEntity.ok().build();
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

}
