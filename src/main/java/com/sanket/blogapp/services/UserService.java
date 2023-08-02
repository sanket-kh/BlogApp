package com.sanket.blogapp.services;


import com.sanket.blogapp.payloads.UserDto;
import com.sanket.blogapp.util.ResponseObject;

public interface UserService {

    ResponseObject createUser(UserDto user);

    ResponseObject updateUser(UserDto user, Long userId);

    ResponseObject getUserById(Long userId);

    ResponseObject getAllUsers();

    ResponseObject deleteUser(Long userId);

}
