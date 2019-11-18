package com.ikhatko.testapp.controller;

import com.ikhatko.testapp.exception.DuplicateProductException;
import com.ikhatko.testapp.exception.NotExistsException;
import com.ikhatko.testapp.exception.ProductAlreadyInOrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controller advice to catch and process any kind of error in controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
  private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(javax.validation.ConstraintViolationException.class)
  public ResponseEntity<String> handleException(javax.validation.ConstraintViolationException ex) {
    logger.error(ex.getMessage());
    String errors = ex.getConstraintViolations()
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(", "));
    return new ResponseEntity<>("Validation errors: " + errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ConversionFailedException.class)
  public ResponseEntity<String> handleException(ConversionFailedException ex) {
    logger.error(ex.getMessage());
    return new ResponseEntity<>("Date format exception, please use dd-MM-yyyy-HH:mm", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleException(MethodArgumentNotValidException  ex) {
    logger.error(ex.getMessage());
    String errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(", "));
    return new ResponseEntity<>("Validation errors: " + errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
  public ResponseEntity<String> handleException(org.hibernate.exception.ConstraintViolationException ex) {
    logger.error(ex.getMessage());
    return new ResponseEntity<>("Data problems", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NotExistsException.class)
  public ResponseEntity<String> handleException(NotExistsException ex) {
    logger.error(ex.getMessage());
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.GONE);
  }

  @ExceptionHandler(DuplicateProductException.class)
  public ResponseEntity<String> handleException(DuplicateProductException ex) {
    logger.error(ex.getMessage());
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ProductAlreadyInOrderException.class)
  public ResponseEntity<String> handleException(ProductAlreadyInOrderException ex) {
    logger.error(ex.getMessage());
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleException(HttpMessageNotReadableException ex) {
    logger.error(ex.getMessage());
    return new ResponseEntity<>(Objects.requireNonNull(ex.getMessage()).substring(0, ex.getMessage().indexOf(":")), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
