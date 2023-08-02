package com.sanket.blogapp.controllers;

import com.sanket.blogapp.Security.JwtTokenHelper;
import com.sanket.blogapp.payloads.JwtAuthRequest;
import com.sanket.blogapp.payloads.JwtAuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
@AllArgsConstructor
public class AuthController {
    private JwtTokenHelper jwtTokenHelper;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login/")
    public ResponseEntity<JwtAuthResponse> createToken(
        @RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUserName(),request.getPassword());
        String token= jwtTokenHelper.generateToken(userDetailsService.loadUserByUsername(request.getUserName()));
        JwtAuthResponse jwtAuthResponse= new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);

    }

    private void authenticate(String userName, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userName,password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }
}
