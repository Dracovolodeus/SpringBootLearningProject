package org.example.exception.advice;

import org.example.exception.InvalidArgumentException;
import org.example.exception.NameOccupiedException;
import org.example.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  private ResponseEntity<String> handleInvalidArgumentException(InvalidArgumentException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler
  private ResponseEntity<String> handleNameOccupiedException(NameOccupiedException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
  }
}
