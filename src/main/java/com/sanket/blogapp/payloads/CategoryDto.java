package com.sanket.blogapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;
    @NotEmpty(message = "title cannot be empty")
    private String categoryTitle;

    private String categoryDescription;
}
