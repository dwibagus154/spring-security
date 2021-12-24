package com.dwibagus.springsecurity.payload;

import lombok.Data;

@Data
public class UsernamePassword {
    private String username;
    private String password;
    private String email;
    private Integer isAdmin;
}
