package com.dwibagus.springsecurity.service.impl;

import com.dwibagus.springsecurity.model.User;
import com.dwibagus.springsecurity.payload.TokenResponse;
import com.dwibagus.springsecurity.payload.UsernamePassword;
import com.dwibagus.springsecurity.repository.UserRepository;
import com.dwibagus.springsecurity.security.JwtTokenProvider;
import com.dwibagus.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UsernamePassword req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setIsAdmin(req.getIsAdmin());
        return userRepository.save(user);
    }

    @Override
    public TokenResponse generateToken(UsernamePassword req) {
        try {
            System.out.println("masuk sini");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()
                    )
            );
            System.out.println("do 0");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("do 1");
            String jwt = jwtTokenProvider.generateToken(authentication);
            System.out.println("do 2");
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwt);
            return tokenResponse;
        } catch (BadCredentialsException e) {
            log.error("Bad Credential", e);
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

//    @Override
//    public User loginMember(UsernamePassword req){
//        User user = userRepository.find
//    }

}
