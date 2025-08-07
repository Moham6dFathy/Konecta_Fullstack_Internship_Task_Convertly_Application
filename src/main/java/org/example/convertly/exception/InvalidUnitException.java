package org.example.convertly.exception;

public class InvalidUnitException extends RuntimeException {
  public InvalidUnitException(String message) {
    super(message);
  }
}
