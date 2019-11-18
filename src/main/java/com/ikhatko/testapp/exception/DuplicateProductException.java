package com.ikhatko.testapp.exception;

public class DuplicateProductException extends Exception {
  public DuplicateProductException(String message) {
    super(message);
  }
}
