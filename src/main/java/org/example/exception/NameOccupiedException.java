package org.example.exception;

public class NameOccupiedException extends RuntimeException {
  public NameOccupiedException(String message) {
    super(message);
  }
}
