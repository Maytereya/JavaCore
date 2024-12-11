package com.example.taskmanagement.factory;

import com.example.taskmanagement.model.Task;

public class UrgentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String title, String description) {
        return new Task(title, description, "urgent");
    }
}
