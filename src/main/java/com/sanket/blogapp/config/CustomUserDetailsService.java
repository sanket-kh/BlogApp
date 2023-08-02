package com.sanket.blogapp.config;

import com.sanket.blogapp.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class CustomUserDetailsService implements UserDetailsService {
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepo.findByEmail(username).isPresent()) {
            return userRepo.findByEmail(username).get();
        } else {
            throw new UsernameNotFoundException("user doesnt exist with email: "+username);
        }


    }
}
