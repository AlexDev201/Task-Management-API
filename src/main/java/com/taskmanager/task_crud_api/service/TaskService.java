package com.taskmanager.task_crud_api.service;

import com.taskmanager.task_crud_api.dto.TaskRequestDTO;
import com.taskmanager.task_crud_api.dto.TaskResponseDTO;
import com.taskmanager.task_crud_api.entity.Task;
import com.taskmanager.task_crud_api.entity.User;
import com.taskmanager.task_crud_api.entity.Category;
import com.taskmanager.task_crud_api.mapper.TaskMapper;
import com.taskmanager.task_crud_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, UserService userService,
                       CategoryService categoryService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.toResponseDTO(task);
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        // Validar que el usuario existe
        User user = userService.getUserEntityById(taskRequestDTO.getUserId());

        // Validar que la categoría existe
        Category category = categoryService.getCategoryEntityById(taskRequestDTO.getCategoryId());

        // Crear la tarea usando el mapper
        Task task = taskMapper.toEntity(taskRequestDTO, user, category);
        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponseDTO(savedTask);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Validar que el usuario existe
        User user = userService.getUserEntityById(taskRequestDTO.getUserId());

        // Validar que la categoría existe
        Category category = categoryService.getCategoryEntityById(taskRequestDTO.getCategoryId());

        // Actualizar la tarea usando el mapper
        taskMapper.updateEntity(task, taskRequestDTO, user, category);
        Task updatedTask = taskRepository.save(task);

        return taskMapper.toResponseDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    public List<TaskResponseDTO> getTasksByUser(Long userId) {
        User user = userService.getUserEntityById(userId);
        return taskRepository.findByUser(user).stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TaskResponseDTO> getTasksByCategory(Long categoryId) {
        Category category = categoryService.getCategoryEntityById(categoryId);
        return taskRepository.findByCategory(category).stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TaskResponseDTO> getTasksByStatus(String status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks found with this status");
        }
        return tasks.stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TaskResponseDTO> getTasksByUserAndStatus(Long userId, String status) {
        User user = userService.getUserEntityById(userId);
        List<Task> tasks = taskRepository.findByUserAndStatus(user, status);
        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks found for this user and status");
        }
        return tasks.stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}