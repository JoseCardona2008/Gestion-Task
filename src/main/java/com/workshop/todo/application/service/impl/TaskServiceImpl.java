package com.workshop.todo.application.service.impl;

import com.workshop.todo.application.service.TaskService;
import com.workshop.todo.domain.exception.ResourceNotFoundException;
import com.workshop.todo.domain.model.TaskModel;
import com.workshop.todo.infrastructure.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public TaskModel createTask(TaskModel task) {
        task.setId(null);
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskModel getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public TaskModel updateTask(Long id, TaskModel task) {
        TaskModel existingTask = getTaskById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        return taskRepository.save(existingTask);
    }

    @Override
    @Transactional
    public TaskModel markAsCompleted(Long id) {
        TaskModel existingTask = getTaskById(id);
        existingTask.setCompleted(true);
        return taskRepository.save(existingTask);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        TaskModel existingTask = getTaskById(id);
        taskRepository.delete(existingTask);
    }
}
