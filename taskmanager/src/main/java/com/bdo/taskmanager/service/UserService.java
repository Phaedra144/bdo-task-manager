package com.bdo.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.exception.EmailCanNotBeChanged;
import com.bdo.taskmanager.exception.EmailAlreadyExists;
import com.bdo.taskmanager.exception.UserNotFoundException;
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

  public List<User> getAllUser() {
    return userRepository.findAllNonDeletedWithNonDeletedTasks();
  }

  public Optional<User> getUserById(int id) {
    checkUserById(id);
    return userRepository.findByIdWithNonDeletedTasks(id);
  }

  public User save(User user) {
    if (user.getId() != null) {
      updateUser(user);
    }
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
      throw new EmailAlreadyExists("Email already exists");
    }
    byte[] salt = HashingService.generateSalt();
    user.setSalt(salt);
    user.setPassword(HashingService.hashingPassword(user.getPassword(), salt));
    return userRepository.save(user);
  }

  public User updateUser(User user) {
    Optional<User> existingUser = getUserById(user.getId());
    if (!existingUser.get().getEmail().equals(user.getEmail())) {
      throw new EmailCanNotBeChanged("Email can not be changed");
    }
    return userRepository.save(user);
  }

  public void deleteById(int id) {
    checkUserById(id);
    userRepository.deleteById(id);
  }

  private void checkUserById(int id) {
    if (userRepository.findByIdWithNonDeletedTasks(id).isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
  }

}
