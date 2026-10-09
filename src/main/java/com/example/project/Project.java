package com.example.project;

import java.time.LocalDateTime;

public class Project {

    public Long id;
    public String name;
    public String description;
    public LocalDateTime createdAt;

    public Project() {
    }

    public Project(Long id, String name, String description,
            LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
}