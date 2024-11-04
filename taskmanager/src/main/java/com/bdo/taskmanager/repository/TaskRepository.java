package com.bdo.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdo.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
