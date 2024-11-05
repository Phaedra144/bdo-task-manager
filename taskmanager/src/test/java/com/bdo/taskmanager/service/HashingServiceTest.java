package com.bdo.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class HashingServiceTest {

  @Test
  public void testHashingPassword() {
    byte[] salt = HashingService.generateSalt();
    String password = "password";
    String password2 = "password";
    String hashedPassword = HashingService.hashingPassword(password, salt);
    String hashedPassword2 = HashingService.hashingPassword(password2, salt);
    assertNotEquals(password, hashedPassword);
    assertEquals(hashedPassword, hashedPassword2);
  }

  @Test
  public void testHashingPasswordWithDifferentSalt() {
    byte[] salt = HashingService.generateSalt();
    byte[] salt2 = HashingService.generateSalt();
    String password = "password";
    String password2 = "password";
    String hashedPassword = HashingService.hashingPassword(password, salt);
    String hashedPassword2 = HashingService.hashingPassword(password2, salt2);
    assertNotEquals(hashedPassword, hashedPassword2);
  }

  @Test
  public void testHashingPasswordWithDifferentPassword() {
    byte[] salt = HashingService.generateSalt();
    String password = "password";
    String password2 = "password2";
    String hashedPassword = HashingService.hashingPassword(password, salt);
    String hashedPassword2 = HashingService.hashingPassword(password2, salt);
    assertNotEquals(hashedPassword, hashedPassword2);
  }

  @Test
  public void testHashingPasswordWithNull() {
    byte[] salt = HashingService.generateSalt();
    String hashedPassword = HashingService.hashingPassword(null, salt);
    assertNull(hashedPassword);
  }

  @Test
  public void testHashingSaltWithNull() {
    byte[] salt = null;
    String password = "password";
    String hashedPassword = HashingService.hashingPassword(password, salt);
    assertNull(hashedPassword);
  }

}
