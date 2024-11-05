package com.bdo.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExists extends RuntimeException {
  public EmailAlreadyExists(String message) {
    super(message);
  }

}
