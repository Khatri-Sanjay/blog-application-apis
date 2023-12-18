package com.sanjay.blog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long categoryId;

    @NotBlank
    @Size(min = 4, message = "Title size should be more than 3")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Description size should be more than 10")
    private String categoryDescription;
}
