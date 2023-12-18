package com.sanjay.blog.services;

import com.sanjay.blog.dto.CategoryDto;
import com.sanjay.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategory();

    void deleteCategoryById(Long id);
}
