package com.bdo.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bdo.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.id = :id")
  Optional<User> findById(@Param("id") Integer id);

  @Query("SELECT u FROM User u WHERE u.id = :id AND u.deleted = false")
  Optional<User> findByIdNonDeleted(@Param("id") Integer id);

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.tasks t WHERE u.id = :id AND u.deleted = false AND (t.deleted = false OR t.deleted IS NULL)")
  Optional<User> findByIdWithNonDeletedTasks(@Param("id") Integer id);

  @Query("select u from User u left join fetch u.tasks t where u.deleted = false and (t.deleted = false or t.deleted is null)")
  List<User> findAllNonDeletedWithNonDeletedTasks();
}
