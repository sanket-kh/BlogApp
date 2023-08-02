package com.sanket.blogapp.controllers;


import com.sanket.blogapp.payloads.UserDto;
import com.sanket.blogapp.services.UserService;
import com.sanket.blogapp.util.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    //post
    @PostMapping("/")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto) {
        ResponseObject responseObject = this.userService.createUser(userDto);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());

    }

    //put
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Long id) {
        ResponseObject responseObject = this.userService.updateUser(userDto, id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        ResponseObject responseObject = this.userService.deleteUser(id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }


    //get
    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllUsers() {
        ResponseObject responseObject = this.userService.getAllUsers();
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        ResponseObject responseObject = this.userService.getUserById(id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());

    }
}
