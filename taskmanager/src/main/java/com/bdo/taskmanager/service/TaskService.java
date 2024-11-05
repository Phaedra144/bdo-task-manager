package com.bdo.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.exception.TaskNotFoundException;
import com.bdo.taskmanager.exception.UserNotFoundException;
import com.bdo.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

  private TaskRepository taskRepository;
  private UserService userService;

  public TaskService(TaskRepository taskRepository, UserService userService) {
    this.taskRepository = taskRepository;
    this.userService = userService;
  }

  public List<Task> getTasksByUserId(int userId) {
    checkUserByUserId(userId);
    return taskRepository.findAllNonDeleted(userId);
  }

  public Optional<Task> getTasksByUserIdAndId(int userId, int id) {
    checkUserByUserId(userId);
    checkTaskByUserIdAndId(userId, id);
    return taskRepository.findByIdNonDeleted(userId, id);
  }

  public Task save(int userId, Task task) {
    User user = getUserByUserId(userId);
    task.setUser(user);
    return taskRepository.save(task);
  }

  public Task update(int userId, Task task) {
    User user = getUserByUserId(userId);
    checkTaskByUserIdAndId(userId, task.getId());
    task.setUser(user);
    return taskRepository.save(task);
  }

  public void deleteById(int userId, int id) {
    checkUserByUserId(userId);
    checkTaskByUserIdAndId(userId, id);
    taskRepository.deleteById(id);
  }

  private void checkUserByUserId(int userId) {
    Optional<User> user = userService.findById(userId);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
  }

  private User getUserByUserId(int userId) {
    checkUserByUserId(userId);
    return userService.findById(userId).get();
  }

  private void checkTaskByUserIdAndId(int userId, int id) {
    Optional<Task> task = taskRepository.findByIdNonDeleted(userId, id);
    if (task.isEmpty()) {
      throw new TaskNotFoundException("Task not found");
    }
  }

}
