package com.workshop.todo.application.service;

import com.workshop.todo.domain.model.TaskModel;

import java.util.List;

public interface TaskService {

    TaskModel createTask(TaskModel task);

    List<TaskModel> getAllTasks();

    TaskModel getTaskById(Long id);

    TaskModel updateTask(Long id, TaskModel task);

    TaskModel markAsCompleted(Long id);

    void deleteTask(Long id);
}
