package com.bdo.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.exception.TaskNotFoundException;
import com.bdo.taskmanager.exception.UserNotFoundException;
import com.bdo.taskmanager.service.TaskService;
import com.bdo.taskmanager.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

  private TaskService taskService;
  private UserService userService;

  public TaskController(TaskService taskService, UserService userService) {
    this.taskService = taskService;
    this.userService = userService;
  }

  @GetMapping("/users/{userId}/tasks")
  public List<Task> getTasks(@PathVariable int userId) {
    return taskService.findAllNonDeleted(userId);
  }

  @GetMapping("/users/{userId}/tasks/{id}")
  public Task getTaskById(@PathVariable int userId, @PathVariable int id) {
    if (taskService.findByIdNonDeleted(userId, id).isEmpty()) {
      throw new TaskNotFoundException("Task not found");
    }
    return taskService.findByIdNonDeleted(userId, id).get();
  }

  @PostMapping("/users/{userId}/tasks")
  public ResponseEntity<Task> postMethodName(@PathVariable int userId, @RequestBody Task entity) {
    Optional<User> user = userService.findById(userId);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    Task savedTask = taskService.save(user.get(), entity);
    return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
  }

  @PutMapping("/users/{userId}/tasks")
  public ResponseEntity<Task> putMethodName(@PathVariable int userId, @RequestBody Task entity) {
    Optional<User> user = userService.findById(userId);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    Optional<Task> task = taskService.findByIdNonDeleted(userId, entity.getId());
    if (task.isEmpty()) {
      throw new TaskNotFoundException("Task not found");
    }
    Task updatedTask = taskService.update(entity, user.get());
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
  }

}
