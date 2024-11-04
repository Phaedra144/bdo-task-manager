package com.bdo.taskmanager.entity;

import jakarta.persistence.Embedded;

public class User {
  private String fullName;
  private String password;
  private String email;
  @Embedded
  private Address address;

  public User() {
  }
  public User(String fullName, String password, String email, Address address) {
    this.fullName = fullName;
    this.password = password;
    this.email = email;
    this.address = address;
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

}
