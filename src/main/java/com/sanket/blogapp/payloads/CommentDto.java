package com.sanket.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String comment;
    private UserDto user;
    private PostDto post;

}
