package com.taskmanager.task_crud_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;

    // Información del usuario (solo datos básicos)
    private Long userId;
    private String username;

    // Información de la categoría (solo datos básicos)
    private Long categoryId;
    private String categoryName;
    private String categoryColor;
}