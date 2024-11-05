package com.bdo.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.service.TaskService;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

  private TaskService taskService;

  public TaskController(TaskService taskservice) {
    this.taskService = taskservice;
  }

  @GetMapping("/tasks")
  public List<Task> getTasks() {
    return taskService.findAllNonDeleted();
  }

  @GetMapping("/tasks/{id}")
  public Task getTaskById(@PathVariable int id) {
    return taskService.findByIdNonDeleted(id).get();
  }

}
