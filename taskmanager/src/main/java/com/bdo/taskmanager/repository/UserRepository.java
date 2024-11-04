package com.bdo.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bdo.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.id = :id")
  Optional<User> findByIdEvenIfDeleted(@Param("id") Integer id);
}
