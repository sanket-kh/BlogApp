package com.sanket.blogapp.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotEmpty(message = "Name cant be empty")
    private String name;

    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "Password must be of minimum 3 characters")
    @Size(min = 2)
    private String password;

    private String about;

}
