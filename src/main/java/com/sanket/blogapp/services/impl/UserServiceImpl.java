package com.sanket.blogapp.services.impl;

import com.sanket.blogapp.entities.User;
import com.sanket.blogapp.payloads.UserDto;
import com.sanket.blogapp.repositories.UserRepo;
import com.sanket.blogapp.services.UserService;
import com.sanket.blogapp.util.ResponseObject;
import com.sanket.blogapp.util.Util;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private ModelMapper modelMapper;




    @Override
    public ResponseObject createUser(UserDto userDto) {


        try {
            User savedUser= this.userRepo.save(this.dtoToUser(userDto));
            return Util.resourceCreated(userDto,"User");
        } catch (Exception e) {
           return Util.resourceNotCreated(userDto, "User");
        }


    }

    @Override
    public ResponseObject updateUser(UserDto userDto, Long userId) {
        User updatedUser = new User();

        if (this.userRepo.findById(userId).isPresent()) {
            User user =  this.userRepo.findById(userId).get();
            userDto.setId(user.getId());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setAbout(userDto.getAbout());

             updatedUser =this.userRepo.save(user);

            return Util.resourceUpdated(updatedUser,"User", userId);
        } else {
            return Util.resourceNotUpdated(updatedUser, "User",userId);
        }


    }

    @Override
    public ResponseObject getUserById(Long userId) {

        if( this.userRepo.findById(userId).isPresent() ){
        User user = this.userRepo.findById(userId).get();
            UserDto  userDto = userToDto(user);
        return Util.resourceFound(userDto,"User");
        }else {

        return Util.resourceNotFound("User");
        }

    }

    @Override
    public ResponseObject getAllUsers() {


        if (this.userRepo.findAll().size()>0) {
            List<User> users = this.userRepo.findAll();
            List<UserDto> userDtos = new ArrayList<>();
            for (User user: users
                 ) {
                UserDto userDto = this.modelMapper.map(user, UserDto.class);
                userDtos.add(userDto);
            }
            return Util.resourceFound(userDtos, "User");
        } else {
            return Util.resourceNotFound("User");
        }

    }

    @Override
    public ResponseObject deleteUser(Long userId) {

        try {
            this.userRepo.deleteById(userId);
          return Util.resourceDeleted("User", userId);

        } catch (Exception e) {
           return Util.resourceNotDeleted("User");
        }

    }

    private User dtoToUser(UserDto userDto){
//        User user= new User();
//        user.setId(userDto.getId()) ;
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return this.modelMapper.map(userDto, User.class);
    }

    private UserDto userToDto(User user){
//        UserDto userDto= new UserDto();
//        userDto.setName(user.getName());
//        userDto.setId(user.getId());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return this.modelMapper.map(user, UserDto.class);
    }

}
