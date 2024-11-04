package com.bdo.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.repository.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAllWithNonDeletedTasks() {
    return userRepository.findAllNonDeletedWithNonDeletedTasks();
  }

  public Optional<User> findByIdWithNonDeletedTasks(int id) {
    return userRepository.findByIdWithNonDeletedTasks(id);
  }

}
