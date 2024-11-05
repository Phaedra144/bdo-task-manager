package com.bdo.taskmanager.controller;

import java.util.List;
import java.util.Optional;

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

import com.bdo.taskmanager.entity.User;
import com.bdo.taskmanager.exception.EmailCanNotBeChanged;
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
    return userService.getAllUser();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable int id) {
    return new ResponseEntity<>(userService.getUserById(id).get(), HttpStatus.OK);
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = userService.save(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @PutMapping("/users")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User updatedUser = userService.updateUser(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable int id) {
    userService.deleteById(id);
    return new ResponseEntity<>(String.format("User with id: %d deleted successfully", id),
        HttpStatus.OK);
  }

}
