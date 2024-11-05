package com.bdo.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bdo.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

  @Query("SELECT t FROM Task t WHERE t.id = :id")
  Optional<Task> findByIdEvenIfDeleted(@Param("id") Integer id);

}
