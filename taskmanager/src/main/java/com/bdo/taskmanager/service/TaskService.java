package com.bdo.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

  private TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository, UserService userService) {
    this.taskRepository = taskRepository;
  }

  public List<Task> findAllNonDeleted(int userId) {
    return taskRepository.findAllNonDeleted(userId);
  }

  public Optional<Task> findByIdNonDeleted(int userId, int id) {
    return taskRepository.findByIdNonDeleted(userId, id);
  }

  public Task save(User user, Task task) {
    task.setUser(user);
    return taskRepository.save(task);
  }

  public Task update(Task task, User user) {
    task.setUser(user);
    return taskRepository.save(task);
  }

  public void deleteById(int id) {
    taskRepository.deleteById(id);
  }

}
