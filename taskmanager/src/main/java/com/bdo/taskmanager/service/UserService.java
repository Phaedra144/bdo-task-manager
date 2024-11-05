package com.bdo.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.hashing.HashingService;
import com.bdo.taskmanager.repository.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> findById(int id) {
    return userRepository.findById(id);
  }

  public List<User> findAllWithNonDeletedTasks() {
    return userRepository.findAllNonDeletedWithNonDeletedTasks();
  }

  public Optional<User> findByIdWithNonDeletedTasks(int id) {
    return userRepository.findByIdWithNonDeletedTasks(id);
  }

  public User save(User user) {
    byte[] salt = HashingService.generateSalt();
    user.setSalt(salt);
    user.setPassword(HashingService.hashingPassword(user.getPassword(), salt));
    return userRepository.save(user);
  }

  public User updateUser(int existingUserId, User user) {
    user.setId(existingUserId);
    return userRepository.save(user);
  }

  public void deleteById(int id) {
    userRepository.deleteById(id);
  }

}
