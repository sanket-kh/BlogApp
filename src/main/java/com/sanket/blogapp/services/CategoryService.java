package com.sanket.blogapp.services;

import com.sanket.blogapp.payloads.CategoryDto;
import com.sanket.blogapp.util.ResponseObject;

public interface CategoryService {

     ResponseObject createCategory(CategoryDto categoryDto);
     ResponseObject updateCategory(CategoryDto categoryDto, Long id);
    ResponseObject deleteCategory(Long id);
     ResponseObject getCategory(Long id);
     ResponseObject getAllCategory();
}
