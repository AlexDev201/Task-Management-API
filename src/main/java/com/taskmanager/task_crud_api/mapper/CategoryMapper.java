package com.taskmanager.task_crud_api.mapper;

import com.taskmanager.task_crud_api.dto.CategoryRequestDTO;
import com.taskmanager.task_crud_api.dto.CategoryResponseDTO;
import com.taskmanager.task_crud_api.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setColor(categoryRequestDTO.getColor());
        return category;
    }

    public CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setDescription(category.getDescription());
        responseDTO.setColor(category.getColor());
        return responseDTO;
    }

    public void updateEntity(Category category, CategoryRequestDTO categoryRequestDTO) {
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setColor(categoryRequestDTO.getColor());
    }
}