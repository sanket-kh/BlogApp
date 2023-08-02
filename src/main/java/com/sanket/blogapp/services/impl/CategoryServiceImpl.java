package com.sanket.blogapp.services.impl;

import com.sanket.blogapp.entities.Category;
import com.sanket.blogapp.payloads.CategoryDto;
import com.sanket.blogapp.repositories.CategoryRepo;
import com.sanket.blogapp.services.CategoryService;
import com.sanket.blogapp.util.ResponseObject;
import com.sanket.blogapp.util.Util;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private ModelMapper modelMapper;
    private CategoryRepo categoryRepo;

    @Override
    public ResponseObject createCategory(CategoryDto categoryDto) {

      try {
          Category category = this.categoryDtoToCategory(categoryDto);
          Category savedCategory = this.categoryRepo.save(category);
          return Util.resourceCreated(savedCategory, "Category");
      }catch (Exception e){
          return Util.resourceNotCreated(categoryDto, "Category");
      }


    }

    @Override
    public ResponseObject updateCategory(CategoryDto categoryDto, Long id) {

        if (this.categoryRepo.findById(id).isPresent()){
            Category category = this.categoryRepo.findById(id).get();
            category.setCategoryTitle(categoryDto.getCategoryTitle());
            category.setCategoryDescription(categoryDto.getCategoryDescription());
            return Util.resourceUpdated(category, "Category", id);

        }else {
          return Util.resourceNotUpdated(categoryDto, "Category", id);
        }

    }

    @Override
    public ResponseObject deleteCategory(Long id) {
        try {
            this.categoryRepo.deleteById(id);
            return Util.resourceDeleted("Category", id);

        } catch (Exception e) {
            return Util.resourceNotDeleted("Category");
        }

    }

    @Override
    public ResponseObject getCategory(Long id) {
        if(this.categoryRepo.findById(id).isPresent()){
            return Util.resourceFound(modelMapper.map(categoryRepo.findById(id).get(), CategoryDto.class) , "Category");
        }else {
            return Util.resourceNotFound( "Category");
        }


    }

    @Override
    public ResponseObject getAllCategory() {
        if (!this.categoryRepo.findAll().isEmpty()) {
            List<CategoryDto> categoryDtos = new ArrayList<>();
            List<Category> categories = categoryRepo.findAll();
            for (Category category: categories
                 ) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDtos.add(modelMapper.map(category, CategoryDto.class));
            }
            return Util.resourceFound(categoryDtos,"categories");

        } else {
            return Util.resourceNotFound("Category");
        }

        }


    public Category categoryDtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto, Category.class);
    }
    public CategoryDto categoryDtoToCategory(Category category){
        return this.modelMapper.map(category, CategoryDto.class);
    }

}
