package com.example.taskmanagement.service;

import com.example.taskmanagement.factory.RegularTaskFactory;
import com.example.taskmanagement.factory.TaskFactory;
import com.example.taskmanagement.factory.UrgentTaskFactory;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.observer.TaskObserver;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static TaskManager instance = new TaskManager(); // Синглтон
    private List<Task> tasks = new ArrayList<>();
    private List<TaskObserver> observers = new ArrayList<>();

    // Приватный конструктор для обеспечения синглтона
    private TaskManager() {}

    public static TaskManager getInstance() {
        return instance;
    }

    // Добавляет наблюдателя
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    // Уведомляет всех наблюдателей
    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.update(task);
        }
    }

    // Метод для добавления задачи
    public void addTask(String type, String title, String description) {
        TaskFactory factory;
        if (type.equalsIgnoreCase("urgent")) {
            factory = new UrgentTaskFactory();
        } else {
            factory = new RegularTaskFactory();
        }

        Task task = factory.createTask(title, description);
        tasks.add(task);
        notifyObservers(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
