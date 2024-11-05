package com.bdo.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

}
