package com.bdo.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmailCanNotBeChanged extends RuntimeException {
  public EmailCanNotBeChanged(String message) {
    super(message);
  }

}
