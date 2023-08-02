package com.sanket.blogapp.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseObject {
    private HttpStatus httpStatus;
    private String message;
    private Object body;

}
