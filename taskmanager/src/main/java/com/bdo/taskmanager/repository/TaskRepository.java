package com.bdo.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bdo.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

  @Query("SELECT t FROM Task t WHERE t.id = :id")
  Optional<Task> findByIdWithDeleted(@Param("id") Integer id);

  @Query("SELECT t FROM Task t WHERE t.deleted = false AND t.id = :id AND t.user.id = :userId")
  Optional<Task> findByIdNonDeleted(@Param("userId") Integer userId, @Param("id") Integer id);

  @Query("SELECT t FROM Task t WHERE t.deleted = false AND t.user.id = :userId")
  List<Task> findAllNonDeleted(@Param("userId") Integer userId);

}
