package com.sanjay.blog.services.impl;

import com.sanjay.blog.dto.CategoryDto;
import com.sanjay.blog.entity.Category;
import com.sanjay.blog.exceptions.ResourceNotFoundException;
import com.sanjay.blog.repository.CategoryRepository;
import com.sanjay.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        category = this.categoryRepository.save(category);
        categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow( ()-> new  ResourceNotFoundException("Category", "Category id", id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updateCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", id));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        // Retrieve all categories from the repository
        List<Category> categoryList = this.categoryRepository.findAll();

        // Convert the list of categories to a list of DTOs using modelMapper
        List<CategoryDto> categoryDtos = categoryList.stream()
                .map(category -> this.modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

        // Return the list of DTOs
        return categoryDtos;
    }


    @Override
    public void deleteCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", id));
        this.categoryRepository.delete(category);
    }
}
