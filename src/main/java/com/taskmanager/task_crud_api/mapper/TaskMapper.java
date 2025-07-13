package com.taskmanager.task_crud_api.mapper;

import com.taskmanager.task_crud_api.dto.TaskRequestDTO;
import com.taskmanager.task_crud_api.dto.TaskResponseDTO;
import com.taskmanager.task_crud_api.entity.Task;
import com.taskmanager.task_crud_api.entity.User;
import com.taskmanager.task_crud_api.entity.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {

    // Convierte TaskRequestDTO a Task (necesita User y Category)
    public Task toEntity(TaskRequestDTO taskRequestDTO, User user, Category category) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        task.setCreated_date(LocalDateTime.now());
        task.setDue_date(taskRequestDTO.getDueDate());
        task.setUser(user);
        task.setCategory(category);
        return task;
    }

    // Convierte Task a TaskResponseDTO (incluye info de User y Category)
    public TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setId(task.getId());
        responseDTO.setTitle(task.getTitle());
        responseDTO.setDescription(task.getDescription());
        responseDTO.setStatus(task.getStatus());
        responseDTO.setCreatedDate(task.getCreated_date());
        responseDTO.setDueDate(task.getDue_date());

        // Información del usuario
        responseDTO.setUserId(task.getUser().getId());
        responseDTO.setUsername(task.getUser().getUsername());

        // Información de la categoría
        responseDTO.setCategoryId(task.getCategory().getId());
        responseDTO.setCategoryName(task.getCategory().getName());
        responseDTO.setCategoryColor(task.getCategory().getColor());

        return responseDTO;
    }

    // Actualiza una entidad existente
    public void updateEntity(Task task, TaskRequestDTO taskRequestDTO, User user, Category category) {
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        task.setDue_date(taskRequestDTO.getDueDate());
        task.setUser(user);
        task.setCategory(category);
        // No actualizamos created_date porque ya existe
    }
}