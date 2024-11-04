package com.bdo.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.exception.UserNotFoundException;
import com.bdo.taskmanager.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userService.findAllWithNonDeletedTasks();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable int id) {
    if (userService.findByIdWithNonDeletedTasks(id).isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    return new ResponseEntity<>(userService.findByIdWithNonDeletedTasks(id).get(), HttpStatus.OK);
  }

}
