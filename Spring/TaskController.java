package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.observer.EmailNotifier;
import com.example.taskmanagement.service.TaskManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskManager taskManager = TaskManager.getInstance();

    public TaskController() {
        taskManager.addObserver(new EmailNotifier()); // Подписка на уведомления
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String type, 
                          @RequestParam String title, 
                          @RequestParam String description) {
        taskManager.addTask(type, title, description);
        return "Задача добавлена успешно!";
    }

    @GetMapping("/list")
    public List<Task> getAllTasks() {
        return taskManager.getTasks();
    }
}
