package com.example.taskmanagement.factory;

import com.example.taskmanagement.model.Task;

public class RegularTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String title, String description) {
        return new Task(title, description, "regular");
    }
}
