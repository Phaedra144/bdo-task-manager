package com.bdo.taskmanager.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashingService {

  public static byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  public static String hashingPassword(String password, byte[] salt) {
    return encodeWithHashing(password, salt);
  }

  private static String encodeWithHashing(String text, byte[] salt) {
    if (text == null || salt == null) {
      return null;
    }
    KeySpec spec = new PBEKeySpec(text.toCharArray(), salt, 65536, 128);
    SecretKeyFactory factory = null;
    byte[] hash = null;
    try {
      factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    } catch (NoSuchAlgorithmException e) {
      System.out.println(e.getMessage());
    }

    try {
      hash = factory.generateSecret(spec).getEncoded();
    } catch (InvalidKeySpecException e) {
      System.out.println(e.getMessage());
    }
    return Base64.getEncoder().encodeToString(hash);
  }

}
