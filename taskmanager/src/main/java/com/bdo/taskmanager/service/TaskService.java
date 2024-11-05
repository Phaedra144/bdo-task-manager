package com.bdo.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

  TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> findAllNonDeleted() {
    return taskRepository.findAllNonDeleted();
  }

  public Optional<Task> findByIdNonDeleted(int id) {
    return taskRepository.findByIdNonDeleted(id);
  }

}
