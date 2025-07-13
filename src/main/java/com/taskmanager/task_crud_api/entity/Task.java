package com.taskmanager.task_crud_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  title;
    private String  description;
    private  String status;
    private LocalDateTime created_date;
    private LocalDateTime due_date;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private  User user;
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
}