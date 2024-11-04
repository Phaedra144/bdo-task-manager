package com.bdo.taskmanager.repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.entity.User;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JpaRepositoryTests {

  @Autowired
  protected TaskRepository taskRepository;

  @Autowired
  protected UserRepository userRepository;

  @Test
  public void getAllUsers() {
    User user = new User("John Doe", "password", "hello@test.com", null, Collections.emptyList());
    User savedUser = userRepository.save(user);
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
    assertArrayEquals(savedUser.getTasks().toArray(),
        users.get(users.size() - 1).getTasks().toArray());
  }

  @Test
  public void testSaveUserWithoutTask() {
    User user = new User("John Doe", "password", "hello@test.com", null, Collections.emptyList());
    User savedUser = userRepository.save(user);
    assertNotNull(savedUser);
    assertEquals("John Doe", savedUser.getFullName());
    assertEquals("hello@test.com", savedUser.getEmail());
  }

  @Test
  public void testSaveUserWithTask() {
    Task task = new Task("Task 1", "Description 1");
    User user = new User("John Doe", "password", "hello@test.com", null,
        Collections.singletonList(task));
    User savedUser = userRepository.save(user);
    Task savedTask = savedUser.getTasks().get(0);
    assertNotNull(savedUser);
    assertEquals("John Doe", savedUser.getFullName());
    assertEquals("hello@test.com", savedUser.getEmail());
    assertEquals("Task 1", savedTask.getTitle());
    assertEquals("Description 1", savedTask.getDescription());
  }

  @Test
  public void testSaveTaskWithoutUser() {
    Task task = new Task("Task 1", "Description 1");
    Task savedTask = taskRepository.save(task);
    assertNotNull(savedTask);
    assertEquals("Task 1", savedTask.getTitle());
  }

  @Test
  public void testSoftDeleteUser() {
    User user = new User("John Doe", "password", "hello@test.com", null, Collections.emptyList());
    User savedUser = userRepository.save(user);
    assertNotNull(savedUser);
    assertEquals("John Doe", savedUser.getFullName());
    assertEquals("hello@test.com", savedUser.getEmail());
    assertFalse(savedUser.isDeleted());
    userRepository.delete(savedUser);
    User deletedUser = userRepository.findByIdEvenIfDeleted(savedUser.getId()).orElse(null);
    assertNotNull(deletedUser);
    assertTrue(deletedUser.isDeleted());
  }

}
