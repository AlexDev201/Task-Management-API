package com.taskmanager.task_crud_api.repository;

import com.taskmanager.task_crud_api.entity.Task;
import com.taskmanager.task_crud_api.entity.User;
import com.taskmanager.task_crud_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByCategory(Category category);
    List<Task> findByStatus(String status);
    List<Task> findByUserAndStatus(User user, String status);
    boolean existsByUser(User user);
    boolean existsByStatus(String status);
}