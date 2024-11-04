package com.bdo.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.repository.TaskRepository;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

  private TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @GetMapping("/tasks/{id}")
  public Task getTask(@PathVariable int id) {
    return taskRepository.findByIdEvenIfDeleted(id).get();
  }

}
