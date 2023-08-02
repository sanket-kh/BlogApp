package com.sanket.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private String imageName;
    private Date date;
    private CategoryDto category;
    private UserDto user;
}
