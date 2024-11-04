package com.bdo.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdo.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
