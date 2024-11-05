package com.bdo.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.service.TaskService;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

  private TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/users/{userId}/tasks")
  public List<Task> getTasks(@PathVariable int userId) {
    return taskService.getTasksByUserId(userId);
  }

  @GetMapping("/users/{userId}/tasks/{id}")
  public Task getTaskById(@PathVariable int userId, @PathVariable int id) {
    return taskService.getTasksByUserIdAndId(userId, id).get();
  }

  @PostMapping("/users/{userId}/tasks")
  public ResponseEntity<Task> createTask(@PathVariable int userId, @RequestBody Task entity) {
    Task savedTask = taskService.save(userId, entity);
    return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
  }

  @PutMapping("/users/{userId}/tasks")
  public ResponseEntity<Task> updateTask(@PathVariable int userId, @RequestBody Task entity) {
    Task updatedTask = taskService.update(userId, entity);
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
  }

  @DeleteMapping("/users/{userId}/tasks/{id}")
  public ResponseEntity<String> deleteTask(@PathVariable int userId, @PathVariable int id) {
    taskService.deleteById(userId, id);
    return new ResponseEntity<>(String.format("Task with id: %d deleted successfully", id),
        HttpStatus.OK);
  }

}
