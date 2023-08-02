package com.sanket.blogapp.controllers;


import com.sanket.blogapp.payloads.CategoryDto;
import com.sanket.blogapp.services.CategoryService;
import com.sanket.blogapp.util.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        ResponseObject responseObject = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") Long id) {
        ResponseObject responseObject = this.categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id) {
        ResponseObject responseObject = this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") Long id) {
        ResponseObject responseObject = this.categoryService.getCategory(id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllCategories() {
        ResponseObject responseObject = this.categoryService.getAllCategory();
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

}
