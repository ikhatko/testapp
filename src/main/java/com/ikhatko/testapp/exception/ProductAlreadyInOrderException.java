package com.ikhatko.testapp.exception;

public class ProductAlreadyInOrderException extends Exception {
  public ProductAlreadyInOrderException(String message) {
    super(message);
  }
}
