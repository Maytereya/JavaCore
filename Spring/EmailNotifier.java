package com.example.taskmanagement.observer;

import com.example.taskmanagement.model.Task;

public class EmailNotifier implements TaskObserver {
    @Override
    public void update(Task task) {
        System.out.println("Уведомление по email: Новая задача добавлена: " + task.getTitle() + " (" + task.getType() + ")");
    }
}
