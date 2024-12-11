package com.example.taskmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private String title;
    private String description;
    private String type; // "urgent" или "regular"
}
