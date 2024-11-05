package com.bdo.taskmanager.repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import com.bdo.taskmanager.entity.Task;
import com.bdo.taskmanager.entity.User;

@ActiveProfiles("test")
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTests {

  @Autowired
  protected TaskRepository taskRepository;

  @Autowired
  protected UserRepository userRepository;

  @Test
  public void testGetAllUsers() {
    User user = new User("John Doe", "password", "hello@test.com", null, Collections.emptyList());
    User savedUser = userRepository.save(user);
    List<User> users = userRepository.findAll();
    assertEquals(1, users.size());
    assertArrayEquals(savedUser.getTasks().toArray(), users.get(0).getTasks().toArray());
  }

  @Test
  public void testTasksAreNotCreatedWithUser() {
    User user = new User("John Doe", "password", "hello@test.com", null, Collections.emptyList());
    userRepository.save(user);
    List<Task> tasks = taskRepository.findAll();
    assertEquals(0, tasks.size());
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
    assertThrows(DataIntegrityViolationException.class, () -> {
      taskRepository.save(task);
    });
  }

  @Test
  public void testSoftDeleteUser() {
    User user = new User("John Doe", null, "hello@test.com", null, Collections.emptyList());
    User savedUser = userRepository.save(user);
    assertNotNull(savedUser);
    assertFalse(savedUser.isDeleted());
    userRepository.delete(savedUser);
    User deletedUser = userRepository.findById(savedUser.getId()).orElse(null);
    assertTrue(deletedUser.isDeleted());
  }

  @Test
  public void testSoftDeleteTask() {
    Task task = new Task("Task 1", "Description 1");
    User user = new User("John Doe", "password", "hello@test.com", null,
        Collections.singletonList(task));
    userRepository.save(user);
    task.setUser(user);
    Task savedTask = taskRepository.save(task);
    assertNotNull(savedTask);
    assertFalse(savedTask.isDeleted());
    taskRepository.delete(savedTask);
    Task deletedTask = taskRepository.findByIdWithDeleted(savedTask.getId()).orElse(null);
    assertTrue(deletedTask.isDeleted());
  }

  @Test
  public void testSoftDeleteUserWithTask() {
    Task task = new Task("Task 1", "Description 1");
    User user = new User("John Doe", "password", "hello@test.com", null,
        Collections.singletonList(task));
    User savedUser = userRepository.save(user);
    task.setUser(user);
    taskRepository.save(task);
    assertNotNull(savedUser);
    assertFalse(savedUser.isDeleted());
    userRepository.delete(savedUser);
    User deletedUser = userRepository.findById(savedUser.getId()).orElse(null);
    assertNotNull(deletedUser);
    assertTrue(deletedUser.isDeleted());
    Task deletedTask = deletedUser.getTasks().get(0);
    assertNotNull(deletedTask);
    assertTrue(deletedTask.isDeleted());
  }

}
