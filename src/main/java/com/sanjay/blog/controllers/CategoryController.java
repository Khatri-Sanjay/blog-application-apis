package com.sanjay.blog.controllers;

import com.sanjay.blog.Response.ApiResponse;
import com.sanjay.blog.dto.CategoryDto;
import com.sanjay.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryById = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryDto, id);
        return ResponseEntity.ok(updatedCategoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully", true));
    }

}
