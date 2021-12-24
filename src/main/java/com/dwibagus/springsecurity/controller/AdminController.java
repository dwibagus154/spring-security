package com.dwibagus.springsecurity.controller;

import com.dwibagus.springsecurity.payload.UsernamePassword;
import com.dwibagus.springsecurity.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")

public class AdminController {

    private final AdminService adminService;

    @PutMapping("/user/{id}")
    public ResponseEntity<?> userActivated(@PathVariable Long id) {
        System.out.println("contoh");
        adminService.userActivate(id);
        return ResponseEntity.ok().build();
    }
}
