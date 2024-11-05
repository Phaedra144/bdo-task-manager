package com.bdo.taskmanager.entity;

import java.util.List;

import org.hibernate.annotations.SQLDelete;

import com.bdo.taskmanager.dto.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String fullName;
  private String password;
  @JsonIgnore
  private byte[] salt;
  private String email;
  @JsonIgnore
  private boolean deleted = Boolean.FALSE;
  @Embedded
  private Address address;
  @OneToMany(
      cascade = { CascadeType.MERGE, CascadeType.REMOVE },
      orphanRemoval = true,
      mappedBy = "user")
  @JsonManagedReference
  private List<Task> tasks;

  public User() {
  }

  public User(String fullName, String password, String email, Address address, List<Task> tasks) {
    this.fullName = fullName;
    this.password = password;
    this.email = email;
    this.address = address;
    this.tasks = tasks;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public byte[] getSalt() {
    return salt;
  }

  public void setSalt(byte[] salt) {
    this.salt = salt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

}
