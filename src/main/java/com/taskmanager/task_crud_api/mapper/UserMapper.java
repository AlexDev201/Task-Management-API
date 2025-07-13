package com.taskmanager.task_crud_api.mapper;
import com.taskmanager.task_crud_api.dto.UserRequestDTO;
import com.taskmanager.task_crud_api.dto.UserResponseDTO;
import com.taskmanager.task_crud_api.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    // Convierte UserRequestDTO a User (para crear/actualizar)
    public User toEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setFullName(userRequestDTO.getFullName());
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }

    // Convierte User a UserResponseDTO (para responder)
    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setFullName(user.getFullName());
        responseDTO.setCreatedDate(user.getCreatedDate());
        return responseDTO;
    }

    // Actualiza una entidad existente con datos del DTO
    public void updateEntity(User user, UserRequestDTO userRequestDTO) {
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setFullName(userRequestDTO.getFullName());
        // No actualizamos createdDate porque ya existe
    }
}
